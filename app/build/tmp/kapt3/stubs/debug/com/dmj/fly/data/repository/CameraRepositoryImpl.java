package com.dmj.fly.data.repository;

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper;
import com.dmj.fly.domain.model.CameraMode;
import com.dmj.fly.domain.model.CameraState;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.repository.CameraRepository;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0018R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/dmj/fly/data/repository/CameraRepositoryImpl;", "Lcom/dmj/fly/domain/repository/CameraRepository;", "keyManagerHelper", "Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;", "(Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;)V", "_mockIsRecording", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_mockIsShootingPhoto", "_mockMode", "Lcom/dmj/fly/domain/model/CameraMode;", "getCameraState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dmj/fly/domain/model/CameraState;", "setMode", "Lcom/dmj/fly/domain/model/Result;", "", "mode", "(Lcom/dmj/fly/domain/model/CameraMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setZoom", "zoomFactor", "", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shootPhoto", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startRecord", "stopRecord", "app_debug"})
public final class CameraRepositoryImpl implements com.dmj.fly.domain.repository.CameraRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.dmj.fly.data.datasource.msdk.KeyManagerHelper keyManagerHelper = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.dmj.fly.domain.model.CameraMode> _mockMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _mockIsRecording = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _mockIsShootingPhoto = null;
    
    @javax.inject.Inject()
    public CameraRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.data.datasource.msdk.KeyManagerHelper keyManagerHelper) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object shootPhoto(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object startRecord(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object stopRecord(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setMode(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.CameraMode mode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setZoom(float zoomFactor, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.CameraState> getCameraState() {
        return null;
    }
}