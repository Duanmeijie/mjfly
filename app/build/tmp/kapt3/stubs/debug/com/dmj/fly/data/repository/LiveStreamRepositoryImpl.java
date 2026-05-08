package com.dmj.fly.data.repository;

import com.dmj.fly.domain.model.LiveStreamConfig;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.repository.LiveStreamRepository;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0016J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0096@\u00a2\u0006\u0002\u0010\u0012R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/dmj/fly/data/repository/LiveStreamRepositoryImpl;", "Lcom/dmj/fly/domain/repository/LiveStreamRepository;", "()V", "_isStreaming", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_streamStatus", "", "getStreamStatus", "Lkotlinx/coroutines/flow/Flow;", "isStreaming", "startStream", "Lcom/dmj/fly/domain/model/Result;", "", "config", "Lcom/dmj/fly/domain/model/LiveStreamConfig;", "(Lcom/dmj/fly/domain/model/LiveStreamConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopStream", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LiveStreamRepositoryImpl implements com.dmj.fly.domain.repository.LiveStreamRepository {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isStreaming = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _streamStatus = null;
    
    @javax.inject.Inject()
    public LiveStreamRepositoryImpl() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object startStream(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.LiveStreamConfig config, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object stopStream(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isStreaming() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getStreamStatus() {
        return null;
    }
}