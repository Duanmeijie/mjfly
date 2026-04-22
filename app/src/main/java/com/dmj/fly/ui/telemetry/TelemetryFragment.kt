package com.dmj.fly.ui.telemetry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dmj.fly.databinding.FragmentTelemetryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TelemetryFragment : Fragment() {

    private var _binding: FragmentTelemetryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TelemetryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelemetryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val status = state.aircraftStatus
                    val telemetry = state.flightTelemetry

                    binding.textConnectionStatus.text = "连接状态: ${if (status.isConnected) "已连接" else "未连接"}"
                    binding.textFlightMode.text = "飞行模式: ${status.flightMode}"
                    binding.textBattery.text = "电量: ${status.batteryPercentage}%"
                    binding.textGps.text = "GPS信号: ${status.gpsSignalLevel}"
                    binding.textFlying.text = "飞行状态: ${if (status.isFlying) "飞行中" else "未起飞"}"
                    binding.textMotors.text = "电机状态: ${if (status.isMotorsOn) "开启" else "关闭"}"
                    binding.textFlightTime.text = "飞行时间: ${status.flightTime}s"

                    binding.textLocation.text = "位置: ${String.format("%.6f", telemetry.latitude)}, ${String.format("%.6f", telemetry.longitude)}"
                    binding.textAltitude.text = "相对高度: ${String.format("%.1f", telemetry.relativeAltitude)} m"
                    binding.textUltrasonic.text = "超声波高度: ${String.format("%.1f", telemetry.ultrasonicHeight)} m"
                    binding.textTakeoffAlt.text = "起飞高度: ${String.format("%.1f", telemetry.takeoffAltitude)} m"
                    binding.textAttitude.text = "姿态: P:${String.format("%.1f", telemetry.pitch)} R:${String.format("%.1f", telemetry.roll)} Y:${String.format("%.1f", telemetry.yaw)}"
                    binding.textVelocity.text = "速度: X:${String.format("%.1f", telemetry.velocityX)} Y:${String.format("%.1f", telemetry.velocityY)} Z:${String.format("%.1f", telemetry.velocityZ)}"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}