package com.dmj.fly.ui

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dmj.fly.R
import com.dmj.fly.databinding.ActivityMainBinding
import com.dmj.fly.sdk.DjiSdkManager
import com.dmj.fly.util.PermissionHelper
import dagger.hilt.android.AndroidEntryPoint

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
        requestPermissions()
    }

    private fun setupPermissionLauncher() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val allGranted = permissions.values.all { it }
            if (allGranted) {
                initializeSdk()
            }
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun initializeSdk() {
        // SDK 已在 FlyApplication 中初始化，这里只需确保 DjiSdkManager 启动连接
        DjiSdkManager.startConnectionToProduct()
    }

    private fun requestPermissions() {
        if (PermissionHelper.hasAllPermissions(this)) {
            initializeSdk()
        } else {
            val missingPermissions = PermissionHelper.getMissingPermissions(this)
            permissionLauncher.launch(missingPermissions.toTypedArray())
        }
    }
}
