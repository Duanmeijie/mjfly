package com.dmj.fly.sdk;

import android.content.Context;
import com.dji.sdk.keyvalue.key.DJIKey;
import com.dji.v5.common.callback.CommonCallbacks;
import com.dji.v5.common.error.IDJIError;
import com.dji.v5.manager.KeyManager;
import com.dji.v5.manager.SDKManager;
import com.dji.v5.manager.datacenter.ProductConnectionState;
import com.dji.v5.manager.datacenter.ProductType;
import com.dmj.fly.domain.model.Result;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/**
 * DJI SDK 管理单例
 * 包名:com.dmj.fly
 * App Key:EE45A36E38A16E49C8CF38A8
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0006\u0010\u0014\u001a\u00020\u0007J\u001f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0016\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u0012\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u0019J\u0013\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u001bJ\u0006\u0010\u001f\u001a\u00020\u0019J*\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190!\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\"\u001a\u0002H\u0011H\u0086@\u00a2\u0006\u0002\u0010#J\u0006\u0010$\u001a\u00020\u0019J\u0006\u0010%\u001a\u00020\u0019R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000f\u00a8\u0006&"}, d2 = {"Lcom/dmj/fly/sdk/DjiSdkManager;", "", "()V", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dmj/fly/sdk/ConnectionState;", "_isRegistered", "", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "isRegistered", "keyManager", "error/NonExistentClass", "Lerror/NonExistentClass;", "getKey", "T", "key", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isProductConnected", "listenKey", "Lkotlinx/coroutines/flow/Flow;", "(Lerror/NonExistentClass;)Lkotlinx/coroutines/flow/Flow;", "onProductConnect", "", "productType", "(Lerror/NonExistentClass;)V", "onProductDisconnect", "onRegisterFailure", "error", "onRegisterSuccess", "setKey", "Lcom/dmj/fly/domain/model/Result;", "value", "(Lerror/NonExistentClass;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startConnectionToProduct", "stopConnectionToProduct", "app_debug"})
public final class DjiSdkManager {
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<com.dmj.fly.sdk.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.sdk.ConnectionState> connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isRegistered = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRegistered = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.Object keyManager = null;
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
    
    /**
     * 初始化 SDK（由 FlyApplication 调用）
     */
    public final void onRegisterSuccess() {
    }
    
    /**
     * 注册失败
     */
    public final void onRegisterFailure(@org.jetbrains.annotations.NotNull()
    IDJIError error) {
    }
    
    /**
     * 产品连接
     */
    public final void onProductConnect(@org.jetbrains.annotations.NotNull()
    ProductType productType) {
    }
    
    /**
     * 产品断开
     */
    public final void onProductDisconnect() {
    }
    
    public final void startConnectionToProduct() {
    }
    
    public final void stopConnectionToProduct() {
    }
    
    public final boolean isProductConnected() {
        return false;
    }
    
    /**
     * 监听 Key 变化
     * ⚠️ 真机测试警告:此功能需在连接真实 DJI 设备且 SDK 注册成功后测试
     */
    @org.jetbrains.annotations.NotNull()
    public final <T extends java.lang.Object>kotlinx.coroutines.flow.Flow<T> listenKey(@org.jetbrains.annotations.NotNull()
    DJIKey<T> key) {
        return null;
    }
    
    /**
     * 获取 Key 值
     * ⚠️ 真机测试警告:此功能需在连接真实 DJI 设备且 SDK 注册成功后测试
     */
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object getKey(@org.jetbrains.annotations.NotNull()
    DJIKey<T> key, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> $completion) {
        return null;
    }
    
    /**
     * 设置 Key 值
     * ⚠️ 真机测试警告:此功能需在连接真实 DJI 设备、GPS 信号良好、环境安全的条件下测试
     */
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object setKey(@org.jetbrains.annotations.NotNull()
    DJIKey<T> key, T value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
}