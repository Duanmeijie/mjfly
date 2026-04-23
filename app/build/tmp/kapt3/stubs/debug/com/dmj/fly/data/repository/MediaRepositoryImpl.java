package com.dmj.fly.data.repository;

import com.dmj.fly.domain.model.MediaFile;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.repository.MediaRepository;
import com.dmj.fly.sdk.DjiSdkManager;
import dji.sdk.keyvalue.value.camera.StorageLocation;
import dji.v5.manager.media.MediaFileInfo;
import dji.v5.manager.media.MediaManager;
import dji.v5.manager.media.MediaStorageInfo;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0010H\u0016J\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00120\tH\u0096@\u00a2\u0006\u0002\u0010\u0013R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/dmj/fly/data/repository/MediaRepositoryImpl;", "Lcom/dmj/fly/domain/repository/MediaRepository;", "()V", "_downloadProgress", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlin/Pair;", "", "", "deleteFile", "Lcom/dmj/fly/domain/model/Result;", "", "mediaFile", "Lcom/dmj/fly/domain/model/MediaFile;", "(Lcom/dmj/fly/domain/model/MediaFile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadFile", "getDownloadProgress", "Lkotlinx/coroutines/flow/Flow;", "refreshFileList", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class MediaRepositoryImpl implements com.dmj.fly.domain.repository.MediaRepository {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Pair<java.lang.String, java.lang.Integer>> _downloadProgress = null;
    
    @javax.inject.Inject
    public MediaRepositoryImpl() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object refreshFileList(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<? extends java.util.List<com.dmj.fly.domain.model.MediaFile>>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object downloadFile(@org.jetbrains.annotations.NotNull
    com.dmj.fly.domain.model.MediaFile mediaFile, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object deleteFile(@org.jetbrains.annotations.NotNull
    com.dmj.fly.domain.model.MediaFile mediaFile, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<kotlin.Pair<java.lang.String, java.lang.Integer>> getDownloadProgress() {
        return null;
    }
}