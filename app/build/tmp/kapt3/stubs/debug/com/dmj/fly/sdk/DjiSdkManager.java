package com.dmj.fly.sdk;

import android.content.Context;
import dji.sdk.sdkmanager.DJISDKManager;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000b\u0010\u0011\u001a\u00020\t\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0014R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0010\u0010\u0010\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/dmj/fly/sdk/DjiSdkManager;", "", "()V", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dmj/fly/sdk/ConnectionState;", "_isRegistered", "", "aircraft", "error/NonExistentClass", "Lerror/NonExistentClass;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "isRegistered", "sdkManager", "getAircraft", "()Lerror/NonExistentClass;", "initialize", "", "context", "Landroid/content/Context;", "startConnectionToProduct", "app_debug"})
public final class DjiSdkManager {
    @org.jetbrains.annotations.Nullable
    private static DJISDKManager sdkManager;
    @org.jetbrains.annotations.Nullable
    private static Aircraft aircraft;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isRegistered = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRegistered = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.MutableStateFlow<com.dmj.fly.sdk.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.sdk.ConnectionState> connectionState = null;
    @org.jetbrains.annotations.NotNull
    public static final com.dmj.fly.sdk.DjiSdkManager INSTANCE = null;
    
    private DjiSdkManager() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRegistered() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.sdk.ConnectionState> getConnectionState() {
        return null;
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public final void startConnectionToProduct() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final Aircraft getAircraft() {
        return null;
    }
}