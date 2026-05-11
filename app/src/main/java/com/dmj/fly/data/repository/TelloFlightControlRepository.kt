package com.dmj.fly.data.repository

import android.util.Log
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.model.Waypoint
import com.dmj.fly.domain.repository.FlightControlRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Tello 无人机飞行控制实现
 * 通过 UDP 协议与 Tello 通信（192.168.10.1:8889）
 *
 * Tello SDK 命令参考：
 * - "command"  : 进入 SDK 模式（必须首先发送）
 * - "takeoff"  : 起飞
 * - "land"     : 降落
 * - "emergency": 紧急停止电机
 * - "rc a b c d": 虚拟摇杆 (a=左右, b=前后, c=上下, d=偏航) 范围 -100~100
 */
@Singleton
class TelloFlightControlRepository @Inject constructor() : FlightControlRepository {

    companion object {
        private const val TAG = "TelloControl"
        private const val TELLO_IP = "192.168.10.1"
        private const val TELLO_PORT = 8889
        private const val RESPONSE_TIMEOUT_MS = 5000
    }

    private val _landingConfirmationNeeded = MutableStateFlow(false)
    private var sdkModeInitialized = false
    private var socket: DatagramSocket? = null

    private fun getSocket(): DatagramSocket {
        if (socket == null || socket!!.isClosed) {
            socket = DatagramSocket().apply {
                soTimeout = RESPONSE_TIMEOUT_MS
            }
        }
        return socket!!
    }

    /**
     * 发送 UDP 命令到 Tello 并等待响应
     */
    private suspend fun sendCommand(command: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            // 首次命令前自动进入 SDK 模式
            if (!sdkModeInitialized && command != "command") {
                val initResult = doSendCommand("command")
                if (initResult is Result.Success) {
                    sdkModeInitialized = true
                    Log.i(TAG, "Tello SDK mode initialized")
                } else {
                    return@withContext Result.Error("Failed to enter SDK mode")
                }
            }

            doSendCommand(command)
        } catch (e: Exception) {
            Log.e(TAG, "Command '$command' failed: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    private fun doSendCommand(command: String): Result<String> {
        return try {
            val socket = getSocket()
            val address = InetAddress.getByName(TELLO_IP)
            val sendData = command.toByteArray()
            val sendPacket = DatagramPacket(sendData, sendData.size, address, TELLO_PORT)

            Log.i(TAG, "Sending: $command")
            socket.send(sendPacket)

            // 接收响应
            val receiveData = ByteArray(1024)
            val receivePacket = DatagramPacket(receiveData, receiveData.size)
            socket.receive(receivePacket)

            val response = String(receivePacket.data, 0, receivePacket.length).trim()
            Log.i(TAG, "Response: $response")

            if (response.equals("ok", ignoreCase = true) || response.equals("OK", ignoreCase = true)) {
                Result.Success(response)
            } else if (response.startsWith("error", ignoreCase = true)) {
                Result.Error(response)
            } else {
                // 查询命令返回数据
                Result.Success(response)
            }
        } catch (e: java.net.SocketTimeoutException) {
            Log.e(TAG, "Command timeout: $command")
            Result.Error("Command timeout")
        } catch (e: Exception) {
            Log.e(TAG, "Send failed: ${e.message}")
            Result.Error(e.message ?: "Send failed")
        }
    }

    override suspend fun takeOff(): Result<Unit> {
        return when (val result = sendCommand("takeoff")) {
            is Result.Success -> Result.Success(Unit)
            is Result.Error -> Result.Error(result.message)
        }
    }

    override suspend fun land(): Result<Unit> {
        return when (val result = sendCommand("land")) {
            is Result.Success -> Result.Success(Unit)
            is Result.Error -> Result.Error(result.message)
        }
    }

    override suspend fun confirmLanding(): Result<Unit> {
        _landingConfirmationNeeded.value = false
        return land()
    }

    override suspend fun startRTH(): Result<Unit> {
        // Tello 没有 RTH 功能，执行降落代替
        Log.i(TAG, "Tello has no RTH, landing instead")
        return land()
    }

    override suspend fun cancelRTH(): Result<Unit> {
        return Result.Success(Unit)
    }

    override suspend fun enableVirtualStick(): Result<Unit> {
        // Tello 的 rc 命令不需要预先启用虚拟摇杆
        if (!sdkModeInitialized) {
            val result = sendCommand("command")
            if (result is Result.Success) {
                sdkModeInitialized = true
            }
            return when (result) {
                is Result.Success -> Result.Success(Unit)
                is Result.Error -> Result.Error(result.message)
            }
        }
        return Result.Success(Unit)
    }

    override suspend fun disableVirtualStick(): Result<Unit> {
        // 发送零值停止移动
        sendCommand("rc 0 0 0 0")
        return Result.Success(Unit)
    }

    override suspend fun sendVirtualStickData(
        pitch: Float,
        roll: Float,
        yaw: Float,
        throttle: Float
    ): Result<Unit> {
        // Tello rc 命令格式: rc <left_right> <forward_backward> <up_down> <yaw>
        // 值范围: -100 ~ 100
        val lr = (roll * 100).toInt().coerceIn(-100, 100)      // roll → 左右
        val fb = (pitch * 100).toInt().coerceIn(-100, 100)     // pitch → 前后
        val ud = (throttle * 100).toInt().coerceIn(-100, 100)  // throttle → 上下
        val yw = (yaw * 100).toInt().coerceIn(-100, 100)       // yaw → 偏航

        return withContext(Dispatchers.IO) {
            try {
                val command = "rc $lr $fb $ud $yw"
                val socket = getSocket()
                val address = InetAddress.getByName(TELLO_IP)
                val sendData = command.toByteArray()
                val sendPacket = DatagramPacket(sendData, sendData.size, address, TELLO_PORT)
                socket.send(sendPacket)
                // rc 命令不需要等待响应（高频发送）
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e.message ?: "RC send failed")
            }
        }
    }

    override suspend fun uploadWayline(waypoints: List<Waypoint>): Result<Unit> {
        return Result.Error("Tello does not support wayline missions")
    }

    override suspend fun startWayline(): Result<Unit> {
        return Result.Error("Tello does not support wayline missions")
    }

    override suspend fun pauseWayline(): Result<Unit> {
        return Result.Error("Tello does not support wayline missions")
    }

    override suspend fun resumeWayline(): Result<Unit> {
        return Result.Error("Tello does not support wayline missions")
    }

    override suspend fun stopWayline(): Result<Unit> {
        return Result.Error("Tello does not support wayline missions")
    }

    override fun isLandingConfirmationNeeded(): Flow<Boolean> = _landingConfirmationNeeded
}
