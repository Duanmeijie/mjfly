package com.dmj.fly.data.datasource.tello

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.DatagramPacket
import java.net.DatagramSocket
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Tello 无人机状态数据接收器
 * 监听 UDP 端口 8890，解析 Tello 发送的实时状态字符串
 *
 * 状态格式示例：
 * pitch:0;roll:0;yaw:0;vgx:0;vgy:0;vgz:0;templ:62;temph:65;tof:10;h:0;bat:87;baro:170.49;time:0;agx:-9.00;agy:-5.00;agz:-1002.00;
 */
@Singleton
class TelloStateReceiver @Inject constructor() {

    companion object {
        private const val TAG = "TelloState"
        private const val STATE_PORT = 8890
    }

    private var receiveJob: Job? = null
    private var socket: DatagramSocket? = null

    private val _state = MutableStateFlow(TelloState())
    val state: StateFlow<TelloState> = _state.asStateFlow()

    fun startReceiving(scope: CoroutineScope) {
        if (receiveJob?.isActive == true) return

        receiveJob = scope.launch(Dispatchers.IO) {
            try {
                socket = DatagramSocket(STATE_PORT)
                socket!!.soTimeout = 3000
                Log.i(TAG, "Listening for Tello state on port $STATE_PORT")

                val buffer = ByteArray(1024)
                while (isActive) {
                    try {
                        val packet = DatagramPacket(buffer, buffer.size)
                        socket!!.receive(packet)
                        val stateString = String(packet.data, 0, packet.length).trim()
                        parseState(stateString)
                    } catch (e: java.net.SocketTimeoutException) {
                        // 超时正常，继续监听
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "State receiver error: ${e.message}")
            } finally {
                socket?.close()
                socket = null
            }
        }
    }

    fun stopReceiving() {
        receiveJob?.cancel()
        receiveJob = null
        socket?.close()
        socket = null
    }

    private fun parseState(stateString: String) {
        try {
            val fields = stateString.split(";")
                .filter { it.contains(":") }
                .associate {
                    val parts = it.split(":")
                    parts[0] to parts[1]
                }

            _state.value = TelloState(
                pitch = fields["pitch"]?.toIntOrNull() ?: 0,
                roll = fields["roll"]?.toIntOrNull() ?: 0,
                yaw = fields["yaw"]?.toIntOrNull() ?: 0,
                speedX = fields["vgx"]?.toIntOrNull() ?: 0,
                speedY = fields["vgy"]?.toIntOrNull() ?: 0,
                speedZ = fields["vgz"]?.toIntOrNull() ?: 0,
                tempLow = fields["templ"]?.toIntOrNull() ?: 0,
                tempHigh = fields["temph"]?.toIntOrNull() ?: 0,
                tofDistance = fields["tof"]?.toIntOrNull() ?: 0,
                height = fields["h"]?.toIntOrNull() ?: 0,
                battery = fields["bat"]?.toIntOrNull() ?: 0,
                barometer = fields["baro"]?.toFloatOrNull() ?: 0f,
                flightTime = fields["time"]?.toIntOrNull() ?: 0,
                accelerationX = fields["agx"]?.toFloatOrNull() ?: 0f,
                accelerationY = fields["agy"]?.toFloatOrNull() ?: 0f,
                accelerationZ = fields["agz"]?.toFloatOrNull() ?: 0f
            )
        } catch (e: Exception) {
            Log.e(TAG, "Parse state failed: ${e.message}")
        }
    }
}

data class TelloState(
    val pitch: Int = 0,
    val roll: Int = 0,
    val yaw: Int = 0,
    val speedX: Int = 0,
    val speedY: Int = 0,
    val speedZ: Int = 0,
    val tempLow: Int = 0,
    val tempHigh: Int = 0,
    val tofDistance: Int = 0,
    val height: Int = 0,
    val battery: Int = 0,
    val barometer: Float = 0f,
    val flightTime: Int = 0,
    val accelerationX: Float = 0f,
    val accelerationY: Float = 0f,
    val accelerationZ: Float = 0f
) {
    val temperature: Int get() = (tempLow + tempHigh) / 2
}
