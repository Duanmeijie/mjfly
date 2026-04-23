package com.dmj.fly.data.repository;

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper;
import com.dmj.fly.domain.model.CameraMode;
import com.dmj.fly.domain.model.CameraState;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.repository.CameraRepository;
import dji.sdk.keyvalue.key.CameraKey;
import dji.sdk.keyvalue.value.camera.CameraModeType;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0096@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0096@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0096@\u00a2\u0006\u0002\u0010\u0013J0\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\t*\b\u0012\u0004\u0012\u00020\n0\t2\u0016\u0010\u0017\u001a\u0012\u0012\b\u0012\u00060\u0019j\u0002`\u001a\u0012\u0004\u0012\u00020\n0\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/dmj/fly/data/repository/CameraRepositoryImpl;", "Lcom/dmj/fly/domain/repository/CameraRepository;", "keyManagerHelper", "Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;", "(Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;)V", "getCameraState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dmj/fly/domain/model/CameraState;", "setMode", "Lcom/dmj/fly/domain/model/Result;", "", "mode", "Lcom/dmj/fly/domain/model/CameraMode;", "(Lcom/dmj/fly/domain/model/CameraMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setZoom", "zoomFactor", "", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shootPhoto", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startRecord", "stopRecord", "onFailure", "action", "Lkotlin/Function1;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "app_debug"})
public final class CameraRepositoryImpl implements com.dmj.fly.domain.repository.CameraRepository {
    @org.jetbrains.annotations.NotNull
    private final com.dmj.fly.data.datasource.msdk.KeyManagerHelper keyManagerHelper = null;
    
    @javax.inject.Inject
    public CameraRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.dmj.fly.data.datasource.msdk.KeyManagerHelper keyManagerHelper) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object shootPhoto(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object startRecord(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object stopRecord(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object setMode(@org.jetbrains.annotations.NotNull
    com.dmj.fly.domain.model.CameraMode mode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object setZoom(float zoomFactor, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.CameraState> getCameraState() {
        return null;
    }
    
    private final com.dmj.fly.domain.model.Result<kotlin.Unit> onFailure(com.dmj.fly.domain.model.Result<kotlin.Unit> $this$onFailure, kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> action) {
        return null;
    }
}