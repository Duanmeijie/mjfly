package com.dmj.fly.ui

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dmj.fly.R
import com.dmj.fly.databinding.ActivityMainBinding
import com.dmj.fly.sdk.DjiSdkManager
import com.dmj.fly.util.PermissionHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPermissionLauncher()
        setupNavigation()
        observeSdkState()
        requestPermissions()
    }

    private fun setupPermissionLauncher() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val allGranted = permissions.values.all { it }
            if (allGranted) {
                DjiSdkManager.startConnectionToProduct()
            }
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun observeSdkState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                DjiSdkManager.isRegistered.collect { isRegistered ->
                    if (isRegistered) {
                        DjiSdkManager.startConnectionToProduct()
                    }
                }
            }
        }
    }

    private fun requestPermissions() {
        if (PermissionHelper.hasAllPermissions(this)) {
            DjiSdkManager.startConnectionToProduct()
        } else {
            val missingPermissions = PermissionHelper.getMissingPermissions(this)
            permissionLauncher.launch(missingPermissions.toTypedArray())
        }
    }
}