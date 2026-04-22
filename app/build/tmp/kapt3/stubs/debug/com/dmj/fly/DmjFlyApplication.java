package com.dmj.fly;

import android.app.Application;
import com.dmj.fly.sdk.DjiSdkManager;
import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

@dagger.hilt.android.HiltAndroidApp
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016\u00a8\u0006\u0006"}, d2 = {"Lcom/dmj/fly/DmjFlyApplication;", "Landroid/app/Application;", "()V", "initTimber", "", "onCreate", "app_debug"})
public final class DmjFlyApplication extends android.app.Application {
    
    public DmjFlyApplication() {
        super();
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    private final void initTimber() {
    }
}