package com.dmj.fly.ui.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmj.fly.databinding.FragmentMediaBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MediaFragment : Fragment() {

    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MediaViewModel by viewModels()

    private lateinit var adapter: MediaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupButtons()
        observeState()
        viewModel.loadFiles()
    }

    private fun setupRecyclerView() {
        adapter = MediaAdapter(
            onDownloadClick = { file -> viewModel.downloadFile(file) },
            onDeleteClick = { file -> showDeleteConfirmation(file) }
        )
        binding.recyclerMedia.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerMedia.adapter = adapter
    }

    private fun setupButtons() {
        binding.btnRefresh.setOnClickListener { viewModel.loadFiles() }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    adapter.submitList(state.files)
                    adapter.setDownloadProgress(state.downloadProgress)
                    binding.progressLoading.isVisible = state.isLoading
                }
            }
        }
    }

    private fun showDeleteConfirmation(file: com.dmj.fly.domain.model.MediaFile) {
        AlertDialog.Builder(requireContext())
            .setTitle("删除确认")
            .setMessage("确定要删除 ${file.fileName} 吗？")
            .setPositiveButton("删除") { _, _ ->
                viewModel.deleteFile(file)
            }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}