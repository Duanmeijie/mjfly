package com.dmj.fly.data.repository;

import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.model.Waypoint;
import com.dmj.fly.domain.repository.FlightControlRepository;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ*\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0082@\u00a2\u0006\u0002\u0010\u0014J\n\u0010\u0015\u001a\u0004\u0018\u00010\fH\u0002J\n\u0010\u0016\u001a\u0004\u0018\u00010\fH\u0002J\n\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\fH\u0002J\"\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\fH\u0002J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u001eH\u0016J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ4\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020$H\u0096@\u00a2\u0006\u0002\u0010(J\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\tJ\"\u0010-\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/H\u0096@\u00a2\u0006\u0002\u00101R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/dmj/fly/data/repository/FlightControlRepositoryImpl;", "Lcom/dmj/fly/domain/repository/FlightControlRepository;", "()V", "_landingConfirmationNeeded", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "cancelRTH", "Lcom/dmj/fly/domain/model/Result;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmLanding", "createCallback", "", "actionName", "", "disableVirtualStick", "enableVirtualStick", "executeFlightAction", "action", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAircraft", "getFlightController", "getGoHomeController", "getVirtualStick", "invokeMethod", "obj", "methodName", "callback", "isLandingConfirmationNeeded", "Lkotlinx/coroutines/flow/Flow;", "land", "pauseWayline", "resumeWayline", "sendVirtualStickData", "pitch", "", "roll", "yaw", "throttle", "(FFFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startRTH", "startWayline", "stopWayline", "takeOff", "uploadWayline", "waypoints", "", "Lcom/dmj/fly/domain/model/Waypoint;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class FlightControlRepositoryImpl implements com.dmj.fly.domain.repository.FlightControlRepository {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _landingConfirmationNeeded = null;
    
    @javax.inject.Inject()
    public FlightControlRepositoryImpl() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object takeOff(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object land(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object confirmLanding(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object startRTH(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object cancelRTH(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object enableVirtualStick(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object disableVirtualStick(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendVirtualStickData(float pitch, float roll, float yaw, float throttle, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object uploadWayline(@org.jetbrains.annotations.NotNull()
    java.util.List<com.dmj.fly.domain.model.Waypoint> waypoints, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object startWayline(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object pauseWayline(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object resumeWayline(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object stopWayline(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLandingConfirmationNeeded() {
        return null;
    }
    
    private final java.lang.Object executeFlightAction(java.lang.String actionName, kotlin.jvm.functions.Function0<kotlin.Unit> action, kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    private final java.lang.Object getAircraft() {
        return null;
    }
    
    private final java.lang.Object getFlightController() {
        return null;
    }
    
    private final java.lang.Object getGoHomeController() {
        return null;
    }
    
    private final java.lang.Object getVirtualStick() {
        return null;
    }
    
    private final void invokeMethod(java.lang.Object obj, java.lang.String methodName, java.lang.Object callback) {
    }
    
    private final java.lang.Object createCallback(java.lang.String actionName) {
        return null;
    }
}