package com.dmj.fly.data.repository;

import com.dmj.fly.domain.model.LiveStreamConfig;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.repository.LiveStreamRepository;
import dji.v5.common.error.IDJIError;
import dji.v5.manager.livestream.LiveStreamManager;
import dji.v5.manager.livestream.LiveStreamSettings;
import dji.v5.manager.livestream.enums.LiveStreamMode;
import dji.v5.manager.livestream.enums.LiveStreamEnvType;
import dji.v5.manager.livestream.enums.VideoEncoderType;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016H\u0096@\u00a2\u0006\u0002\u0010\u001bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/dmj/fly/data/repository/LiveStreamRepositoryImpl;", "Lcom/dmj/fly/domain/repository/LiveStreamRepository;", "()V", "_isStreaming", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_streamStatus", "", "liveStreamManager", "error/NonExistentClass", "getLiveStreamManager", "()Lerror/NonExistentClass;", "liveStreamManager$delegate", "Lkotlin/Lazy;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getStreamStatus", "Lkotlinx/coroutines/flow/Flow;", "isStreaming", "registerLiveStreamListener", "", "startStream", "Lcom/dmj/fly/domain/model/Result;", "config", "Lcom/dmj/fly/domain/model/LiveStreamConfig;", "(Lcom/dmj/fly/domain/model/LiveStreamConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopStream", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LiveStreamRepositoryImpl implements com.dmj.fly.domain.repository.LiveStreamRepository {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isStreaming = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _streamStatus = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy liveStreamManager$delegate = null;
    
    @javax.inject.Inject
    public LiveStreamRepositoryImpl() {
        super();
    }
    
    private final LiveStreamManager getLiveStreamManager() {
        return null;
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