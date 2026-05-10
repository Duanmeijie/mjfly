package com.dmj.fly.data.repository

import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.model.Waypoint
import com.dmj.fly.domain.repository.FlightControlRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightControlRepositoryImpl @Inject constructor() : FlightControlRepository {

    private val _landingConfirmationNeeded = MutableStateFlow(false)

    override suspend fun takeOff(): Result<Unit> {
        return executeFlightAction("takeOff") {
            invokeMethod(getFlightController(), "startTakeoff", createCallback("takeOff"))
        }
    }

    override suspend fun land(): Result<Unit> {
        return executeFlightAction("land") {
            invokeMethod(getFlightController(), "startLanding", createCallback("land"))
        }
    }

    override suspend fun confirmLanding(): Result<Unit> {
        return executeFlightAction("confirmLanding") {
            val callback = object : Any() {
                @Suppress("unused")
                fun onSuccess() {
                    Timber.d("confirmLanding success")
                    _landingConfirmationNeeded.value = false
                }
                @Suppress("unused")
                fun onFailure(error: Any) {
                    Timber.e("confirmLanding failed: ${error.toString()}")
                }
            }
            invokeMethod(getFlightController(), "confirmLanding", callback)
        }
    }

    override suspend fun startRTH(): Result<Unit> {
        return executeFlightAction("startRTH") {
            val goHomeController = getGoHomeController()
            if (goHomeController != null) {
                invokeMethod(goHomeController, "startGoHome", createCallback("startGoHome"))
            }
        }
    }

    override suspend fun cancelRTH(): Result<Unit> {
        return executeFlightAction("cancelRTH") {
            val goHomeController = getGoHomeController()
            if (goHomeController != null) {
                invokeMethod(goHomeController, "cancelGoHome", createCallback("cancelGoHome"))
            }
        }
    }

    override suspend fun enableVirtualStick(): Result<Unit> {
        return executeFlightAction("enableVirtualStick") {
            val virtualStick = getVirtualStick()
            if (virtualStick != null) {
                invokeMethod(virtualStick, "enable", createCallback("enableVirtualStick"))
            }
        }
    }

    override suspend fun disableVirtualStick(): Result<Unit> {
        return executeFlightAction("disableVirtualStick") {
            val virtualStick = getVirtualStick()
            if (virtualStick != null) {
                invokeMethod(virtualStick, "disable", createCallback("disableVirtualStick"))
            }
        }
    }

    override suspend fun sendVirtualStickData(
        pitch: Float,
        roll: Float,
        yaw: Float,
        throttle: Float
    ): Result<Unit> {
        return try {
            val virtualStick = getVirtualStick()
            if (virtualStick == null) {
                Timber.e("sendVirtualStickData failed: No connected aircraft")
                return Result.Error("No connected aircraft")
            }
            Timber.d("Sending virtual stick data: pitch=$pitch, roll=$roll, yaw=$yaw, throttle=$throttle")
            
            val positionControlDataClass = Class.forName("dji.v5.sdk.flightcontroller.position.PositionControlData")
            val builderClass = Class.forName("dji.v5.sdk.flightcontroller.position.PositionControlData\$Builder")
            val builder = builderClass.getDeclaredConstructor().newInstance()
            
            builderClass.getMethod("pitch", Float::class.java).invoke(builder, pitch)
            builderClass.getMethod("roll", Float::class.java).invoke(builder, roll)
            builderClass.getMethod("yaw", Float::class.java).invoke(builder, yaw)
            builderClass.getMethod("throttle", Float::class.java).invoke(builder, throttle)
            
            val controlData = builderClass.getMethod("build").invoke(builder)
            virtualStick.javaClass.getMethod("sendPositionControlData", positionControlDataClass)
                .invoke(virtualStick, controlData)
            
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e("sendVirtualStickData exception: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun uploadWayline(waypoints: List<Waypoint>): Result<Unit> {
        Timber.d("uploadWayline called with ${waypoints.size} waypoints")
        return Result.Error("Wayline upload requires DJI Waypoints SDK")
    }

    override suspend fun startWayline(): Result<Unit> {
        return Result.Error("Wayline control requires DJI Waypoints SDK")
    }

    override suspend fun pauseWayline(): Result<Unit> {
        return Result.Error("Wayline control requires DJI Waypoints SDK")
    }

    override suspend fun resumeWayline(): Result<Unit> {
        return Result.Error("Wayline control requires DJI Waypoints SDK")
    }

    override suspend fun stopWayline(): Result<Unit> {
        return Result.Error("Wayline control requires DJI Waypoints SDK")
    }

    override fun isLandingConfirmationNeeded(): Flow<Boolean> = _landingConfirmationNeeded

    private suspend fun executeFlightAction(actionName: String, action: () -> Unit): Result<Unit> {
        return try {
            val aircraft = getAircraft()
            if (aircraft == null) {
                Timber.e("$actionName failed: No connected aircraft")
                return Result.Error("No connected aircraft")
            }
            Timber.d("Calling $actionName...")
            action()
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e("$actionName exception: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    private fun getAircraft(): Any? {
        return try {
            val sdkManagerClass = Class.forName("dji.v5.manager.SDKManager")
            val getInstanceMethod = sdkManagerClass.getMethod("getInstance")
            val sdkManager = getInstanceMethod.invoke(null)
            sdkManager.javaClass.getMethod("getAircraft").invoke(sdkManager)
        } catch (e: Exception) {
            Timber.e("Failed to get aircraft: ${e.message}")
            null
        }
    }

    private fun getFlightController(): Any? {
        val aircraft = getAircraft() ?: return null
        return try {
            aircraft.javaClass.getMethod("getFlightController").invoke(aircraft)
        } catch (e: Exception) {
            Timber.e("Failed to get flightController: ${e.message}")
            null
        }
    }

    private fun getGoHomeController(): Any? {
        val flightController = getFlightController() ?: return null
        return try {
            flightController.javaClass.getMethod("getGoHomeController").invoke(flightController)
        } catch (e: Exception) {
            Timber.e("Failed to get goHomeController: ${e.message}")
            null
        }
    }

    private fun getVirtualStick(): Any? {
        val flightController = getFlightController() ?: return null
        return try {
            flightController.javaClass.getMethod("getVirtualStick").invoke(flightController)
        } catch (e: Exception) {
            Timber.e("Failed to get virtualStick: ${e.message}")
            null
        }
    }

    private fun invokeMethod(obj: Any?, methodName: String, callback: Any) {
        if (obj == null) return
        try {
            val callbackClass = Class.forName("dji.v5.common.callback.CommonCallbacks\$CompletionCallback")
            obj.javaClass.getMethod(methodName, callbackClass).invoke(obj, callback)
        } catch (e: Exception) {
            Timber.e("Failed to invoke $methodName: ${e.message}")
        }
    }

    private fun createCallback(actionName: String): Any {
        return object : Any() {
            @Suppress("unused")
            fun onSuccess() {
                Timber.d("$actionName success")
            }
            @Suppress("unused")
            fun onFailure(error: Any) {
                Timber.e("$actionName failed: ${error.toString()}")
            }
        }
    }
}
