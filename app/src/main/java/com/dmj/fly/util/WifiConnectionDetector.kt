package com.dmj.fly.util

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.isActive

/**
 * WiFi 连接检测器
 * 检测手机是否连接到 DJI 无人机的 WiFi 网络
 *
 * DJI WiFi 无人机常见 SSID 前缀：
 * - DJI Mini 3/4 Pro: "DJI Mini 3 Pro-xxxxx" / "DJI Mini 4 Pro-xxxxx"
 * - DJI Mavic Air 2: "MavicAir2_xxxxx"
 * - DJI Air 2S: "Air2S_xxxxx"
 * - DJI Mavic 3: "Mavic3_xxxxx"
 * - DJI Avata: "Avata_xxxxx"
 * - DJI FPV: "DJI-xxxxx"
 * - DJI Mini 2: "MavicMini-xxxxx"
 */
class WifiConnectionDetector(context: Context) {

    companion object {
        private const val TAG = "WifiDetector"

        // DJI WiFi 无人机 SSID 前缀列表
        private val DJI_SSID_PREFIXES = listOf(
            "TELLO",
            "DJI Mini 3 Pro",
            "DJI Mini 4 Pro",
            "DJI Mini 3",
            "DJI Mini 2",
            "MavicMini",
            "Mavic Air",
            "Air2S",
            "Mavic3",
            "Mavic 3",
            "Avata",
            "DJI FPV",
            "DJI Air",
            "DJI Mavic",
            "Mavic2",
            "Mini 4K",
            "DJI Neo"
        )

        // 检查频率：每 2 秒
        private const val CHECK_INTERVAL_MS = 2000L
    }

    private val wifiManager = context.applicationContext.getSystemService(WifiManager::class.java)

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private var monitorJob: Job? = null

    fun startMonitoring(scope: CoroutineScope) {
        monitorJob?.cancel()
        monitorJob = scope.launch(Dispatchers.IO) {
            while (isActive) {
                checkConnection()
                delay(CHECK_INTERVAL_MS)
            }
        }
    }

    fun stopMonitoring() {
        monitorJob?.cancel()
        monitorJob = null
        _isConnected.value = false
    }

    fun checkConnection() {
        val connected = isConnectionDjiDrone()
        if (connected != _isConnected.value) {
            Log.i(TAG, "Connection state changed: connected=$connected, ssid=${getCurrentWifiSsid()}")
        }
        _isConnected.value = connected
    }

    /**
     * 当前网络是否为 DJI 无人机 WiFi
     * 注意：无人机WiFi没有互联网，Android可能不会将其作为activeNetwork
     * 因此直接通过WifiManager获取WiFi连接信息
     */
    fun isConnectionDjiDrone(): Boolean {
        // 直接通过 WifiManager 获取 SSID（不依赖 activeNetwork）
        val ssid = getCurrentWifiSsid() ?: return false
        return DJI_SSID_PREFIXES.any { ssid.startsWith(it, ignoreCase = true) }
    }

    /**
     * 获取当前 WiFi SSID 名称
     */
    fun getCurrentWifiSsid(): String? {
        return try {
            val info = wifiManager?.connectionInfo
            info?.ssid?.removeSurrounding("\"")?.takeIf { it.isNotEmpty() && it != "<unknown ssid>" }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 获取当前连接的 SSID（用于 UI 显示）
     */
    fun getConnectedSsidInfo(): String? {
        val ssid = getCurrentWifiSsid() ?: return null
        if (DJI_SSID_PREFIXES.any { ssid.startsWith(it, ignoreCase = true) }) {
            // 提取型号部分
            val modelName = DJI_SSID_PREFIXES.firstOrNull { ssid.startsWith(it, ignoreCase = true) } ?: "DJI Drone"
            return "$modelName (${ssid.takeLast(5)})"
        }
        return null
    }
}
