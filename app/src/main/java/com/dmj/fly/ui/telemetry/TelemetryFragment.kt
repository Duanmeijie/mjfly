package com.dmj.fly.ui.telemetry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmj.fly.databinding.FragmentTelemetryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TelemetryFragment : Fragment() {

    private var _binding: FragmentTelemetryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelemetryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}