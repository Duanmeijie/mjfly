package com.dmj.fly.domain.repository;

import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.model.Waypoint;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J4\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u00a6@\u00a2\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u00a6@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006\u001f"}, d2 = {"Lcom/dmj/fly/domain/repository/FlightControlRepository;", "", "cancelRTH", "Lcom/dmj/fly/domain/model/Result;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmLanding", "disableVirtualStick", "enableVirtualStick", "isLandingConfirmationNeeded", "Lkotlinx/coroutines/flow/Flow;", "", "land", "pauseWayline", "resumeWayline", "sendVirtualStickData", "pitch", "", "roll", "yaw", "throttle", "(FFFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startRTH", "startWayline", "stopWayline", "takeOff", "uploadWayline", "waypoints", "", "Lcom/dmj/fly/domain/model/Waypoint;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface FlightControlRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object takeOff(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object land(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object confirmLanding(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object startRTH(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object cancelRTH(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object enableVirtualStick(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object disableVirtualStick(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object sendVirtualStickData(float pitch, float roll, float yaw, float throttle, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object uploadWayline(@org.jetbrains.annotations.NotNull
    java.util.List<com.dmj.fly.domain.model.Waypoint> waypoints, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object startWayline(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object pauseWayline(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object resumeWayline(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object stopWayline(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLandingConfirmationNeeded();
}