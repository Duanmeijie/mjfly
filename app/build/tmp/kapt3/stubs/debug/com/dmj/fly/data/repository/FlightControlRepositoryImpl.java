package com.dmj.fly.data.repository;

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.model.Waypoint;
import com.dmj.fly.domain.repository.FlightControlRepository;
import dji.sdk.keyvalue.key.FlightControllerKey;
import dji.sdk.keyvalue.value.flightcontroller.VirtualStickFlightControlData;
import dji.sdk.keyvalue.value.flightcontroller.VirtualStickAdvancedSettings;
import dji.sdk.keyvalue.value.flightcontroller.VirtualStickControlMode;
import dji.sdk.keyvalue.value.flightcontroller.VirtualStickCoordinateSystem;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J4\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0096@\u00a2\u0006\u0002\u0010\u001fJ0\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0016\u0010!\u001a\u0012\u0012\b\u0012\u00060#j\u0002`$\u0012\u0004\u0012\u00020\u00050\"H\u0002J,\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\'H\u0002\u00a8\u0006("}, d2 = {"Lcom/dmj/fly/data/repository/FlightControlRepositoryImpl;", "Lcom/dmj/fly/domain/repository/FlightControlRepository;", "()V", "cancelRTH", "Lcom/dmj/fly/domain/model/Result;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmLanding", "disableVirtualStick", "enableVirtualStick", "isLandingConfirmationNeeded", "Lkotlinx/coroutines/flow/Flow;", "", "land", "pauseWayline", "resumeWayline", "sendVirtualStickData", "pitch", "", "roll", "yaw", "throttle", "(FFFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startRTH", "startWayline", "stopWayline", "takeOff", "uploadWayline", "waypoints", "", "Lcom/dmj/fly/domain/model/Waypoint;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onFailure", "action", "Lkotlin/Function1;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "recoverWith", "block", "Lkotlin/Function0;", "app_debug"})
public final class FlightControlRepositoryImpl implements com.dmj.fly.domain.repository.FlightControlRepository {
    
    @javax.inject.Inject
    public FlightControlRepositoryImpl() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object takeOff(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object land(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object confirmLanding(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object startRTH(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object cancelRTH(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object enableVirtualStick(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object disableVirtualStick(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object sendVirtualStickData(float pitch, float roll, float yaw, float throttle, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object uploadWayline(@org.jetbrains.annotations.NotNull
    java.util.List<com.dmj.fly.domain.model.Waypoint> waypoints, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object startWayline(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object pauseWayline(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object resumeWayline(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object stopWayline(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLandingConfirmationNeeded() {
        return null;
    }
    
    private final com.dmj.fly.domain.model.Result<kotlin.Unit> recoverWith(com.dmj.fly.domain.model.Result<kotlin.Unit> $this$recoverWith, kotlin.jvm.functions.Function0<? extends com.dmj.fly.domain.model.Result<kotlin.Unit>> block) {
        return null;
    }
    
    private final com.dmj.fly.domain.model.Result<kotlin.Unit> onFailure(com.dmj.fly.domain.model.Result<kotlin.Unit> $this$onFailure, kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> action) {
        return null;
    }
}