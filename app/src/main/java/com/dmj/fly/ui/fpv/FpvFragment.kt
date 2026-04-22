package com.dmj.fly.ui.fpv

import android.graphics.SurfaceTexture
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dmj.fly.databinding.FragmentFpvBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FpvFragment : Fragment() {

    private var _binding: FragmentFpvBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FpvViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFpvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextureView()
        setupButtons()
        observeState()
    }

    private fun setupTextureView() {
        binding.textureViewFpv.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(surfaceTexture: SurfaceTexture, width: Int, height: Int) {
                viewModel.startVideoStream(surfaceTexture)
            }

            override fun onSurfaceTextureSizeChanged(surfaceTexture: SurfaceTexture, width: Int, height: Int) {}

            override fun onSurfaceTextureDestroyed(surfaceTexture: SurfaceTexture): Boolean {
                viewModel.stopVideoStream()
                return true
            }

            override fun onSurfaceTextureUpdated(surfaceTexture: SurfaceTexture) {}
        }
    }

    private fun setupButtons() {
        binding.btnTakePhoto.setOnClickListener { viewModel.takePhoto() }
        binding.btnRecord.setOnClickListener { viewModel.toggleRecord() }
        binding.btnTakeOff.setOnClickListener { viewModel.takeOff() }
        binding.btnLand.setOnClickListener { viewModel.land() }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.connectionStatus.collect { status ->
                        binding.textConnectionStatus.text = status
                    }
                }
                launch {
                    viewModel.isRecording.collect { isRecording ->
                        binding.btnRecord.text = if (isRecording) "停止" else "录像"
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}