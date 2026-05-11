package com.dmj.fly.ui.control

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dmj.fly.databinding.FragmentControlBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ControlFragment : Fragment() {

    private var _binding: FragmentControlBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ControlViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVirtualSticks()
        setupButtons()
        observeState()
    }

    private fun setupVirtualSticks() {
        // 左摇杆：上下=上升/下降(throttle)，左右=左转/右转(yaw)
        binding.virtualStickLeft.onStickChanged = { x, y ->
            viewModel.updateVirtualStickData(0f, 0f, x, -y)
        }

        // 右摇杆：上下=前进/后退(pitch)，左右=左移/右移(roll)
        binding.virtualStickRight.onStickChanged = { x, y ->
            viewModel.updateVirtualStickData(-y, x, 0f, 0f)
        }
    }

    private fun setupButtons() {
        binding.btnTakeOff.setOnClickListener { viewModel.takeOff() }
        binding.btnLand.setOnClickListener { viewModel.land() }
        binding.btnRth.setOnClickListener { viewModel.startRth() }
        binding.btnVirtualStick.setOnClickListener {
            if (viewModel.uiState.value.isVirtualStickEnabled) {
                viewModel.disableVirtualStick()
            } else {
                viewModel.enableVirtualStick()
            }
        }
        binding.btnEmergencyStop.setOnClickListener { viewModel.emergencyStop() }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.textConnectionStatus.text = state.connectionStatus
                    binding.textBattery.text = "\uD83D\uDD0B ${state.battery}%"
                    binding.textTemperature.text = "\uD83C\uDF21 ${state.temperature}\u00B0C"
                    binding.btnVirtualStick.text = if (state.isVirtualStickEnabled) "关闭摇杆" else "开启摇杆"

                    if (state.needLandingConfirmation) {
                        showLandingConfirmationDialog()
                    }
                }
            }
        }
    }

    private fun showLandingConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("降落确认")
            .setMessage("是否确认降落？")
            .setPositiveButton("确认") { _, _ ->
                viewModel.confirmLanding()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
