package com.dmj.fly.sdk;

import android.util.Log;
import dji.v5.common.error.IDJIError;
import dji.v5.common.register.DJISDKInitEvent;
import dji.v5.manager.SDKManager;
import dji.v5.manager.interfaces.SDKManagerCallback;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001c\u001a\u00020\u0015J\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f\u00a8\u0006!"}, d2 = {"Lcom/dmj/fly/sdk/DjiSdkManager;", "", "()V", "TAG", "", "_activationState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dmj/fly/sdk/ActivationState;", "_connectionState", "Lcom/dmj/fly/sdk/ConnectionState;", "_isRegistered", "", "activationState", "Lkotlinx/coroutines/flow/StateFlow;", "getActivationState", "()Lkotlinx/coroutines/flow/StateFlow;", "connectionState", "getConnectionState", "isRegistered", "getConnectedAircraft", "initialize", "", "context", "Landroid/content/Context;", "onProductChanged", "productId", "", "onProductConnect", "onProductDisconnect", "onRegisterFailure", "error", "Ldji/v5/common/error/IDJIError;", "onRegisterSuccess", "app_debug"})
public final class DjiSdkManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "DjiSdkManager";
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<com.dmj.fly.sdk.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.sdk.ConnectionState> connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isRegistered = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRegistered = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<com.dmj.fly.sdk.ActivationState> _activationState = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.sdk.ActivationState> activationState = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.dmj.fly.sdk.DjiSdkManager INSTANCE = null;
    
    private DjiSdkManager() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.sdk.ConnectionState> getConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRegistered() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.sdk.ActivationState> getActivationState() {
        return null;
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void onRegisterSuccess() {
    }
    
    public final void onRegisterFailure(@org.jetbrains.annotations.NotNull()
    dji.v5.common.error.IDJIError error) {
    }
    
    public final void onProductConnect(int productId) {
    }
    
    public final void onProductChanged(int productId) {
    }
    
    public final void onProductDisconnect() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getConnectedAircraft() {
        return null;
    }
}