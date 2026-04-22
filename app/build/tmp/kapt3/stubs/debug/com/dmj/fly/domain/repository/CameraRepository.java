package com.dmj.fly.domain.repository;

import com.dmj.fly.domain.model.CameraMode;
import com.dmj.fly.domain.model.CameraState;
import com.dmj.fly.domain.model.Result;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lcom/dmj/fly/domain/repository/CameraRepository;", "", "getCameraState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dmj/fly/domain/model/CameraState;", "setMode", "Lcom/dmj/fly/domain/model/Result;", "", "mode", "Lcom/dmj/fly/domain/model/CameraMode;", "(Lcom/dmj/fly/domain/model/CameraMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setZoom", "zoomFactor", "", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shootPhoto", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startRecord", "stopRecord", "app_debug"})
public abstract interface CameraRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object shootPhoto(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object startRecord(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object stopRecord(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setMode(@org.jetbrains.annotations.NotNull
    com.dmj.fly.domain.model.CameraMode mode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setZoom(float zoomFactor, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.CameraState> getCameraState();
}