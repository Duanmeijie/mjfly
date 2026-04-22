package com.dmj.fly

import android.app.Application
import com.dmj.fly.sdk.DjiSdkManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DmjFlyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        DjiSdkManager.initialize(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}