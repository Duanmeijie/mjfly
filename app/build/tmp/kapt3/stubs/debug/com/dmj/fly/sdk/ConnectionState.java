package com.dmj.fly.sdk;

import android.content.Context;
import dji.sdk.sdkmanager.DJISDKManager;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/dmj/fly/sdk/ConnectionState;", "", "(Ljava/lang/String;I)V", "DISCONNECTED", "CONNECTED", "UNKNOWN", "app_debug"})
public enum ConnectionState {
    /*public static final*/ DISCONNECTED /* = new DISCONNECTED() */,
    /*public static final*/ CONNECTED /* = new CONNECTED() */,
    /*public static final*/ UNKNOWN /* = new UNKNOWN() */;
    
    ConnectionState() {
    }
    
    @org.jetbrains.annotations.NotNull
    public static kotlin.enums.EnumEntries<com.dmj.fly.sdk.ConnectionState> getEntries() {
        return null;
    }
}