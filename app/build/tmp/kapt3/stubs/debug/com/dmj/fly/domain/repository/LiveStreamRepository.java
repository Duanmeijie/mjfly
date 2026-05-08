package com.dmj.fly.domain.repository;

import com.dmj.fly.domain.model.LiveStreamConfig;
import com.dmj.fly.domain.model.Result;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H&J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a6@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/dmj/fly/domain/repository/LiveStreamRepository;", "", "getStreamStatus", "Lkotlinx/coroutines/flow/Flow;", "", "isStreaming", "", "startStream", "Lcom/dmj/fly/domain/model/Result;", "", "config", "Lcom/dmj/fly/domain/model/LiveStreamConfig;", "(Lcom/dmj/fly/domain/model/LiveStreamConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopStream", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface LiveStreamRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object startStream(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.LiveStreamConfig config, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object stopStream(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isStreaming();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getStreamStatus();
}