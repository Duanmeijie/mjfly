package com.dmj.fly.data.repository;

import com.dmj.fly.domain.model.LiveStreamConfig;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.repository.LiveStreamRepository;
import dji.sdk.sdkmanager.DJISDKManager;
import dji.sdk.livestream.LiveStreamManager;
import dji.common.livestream.DJILiveStreamSettings;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0015R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/dmj/fly/data/repository/LiveStreamRepositoryImpl;", "Lcom/dmj/fly/domain/repository/LiveStreamRepository;", "()V", "_isStreaming", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_streamStatus", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getStreamStatus", "Lkotlinx/coroutines/flow/Flow;", "isStreaming", "registerLiveStreamListener", "", "startStream", "Lcom/dmj/fly/domain/model/Result;", "config", "Lcom/dmj/fly/domain/model/LiveStreamConfig;", "(Lcom/dmj/fly/domain/model/LiveStreamConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopStream", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LiveStreamRepositoryImpl implements com.dmj.fly.domain.repository.LiveStreamRepository {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isStreaming = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _streamStatus = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    @javax.inject.Inject
    public LiveStreamRepositoryImpl() {
        super();
    }
    
    private final void registerLiveStreamListener() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object startStream(@org.jetbrains.annotations.NotNull
    com.dmj.fly.domain.model.LiveStreamConfig config, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object stopStream(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isStreaming() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.lang.String> getStreamStatus() {
        return null;
    }
}