package com.dmj.fly.domain.repository;

import com.dmj.fly.domain.model.MediaFile;
import com.dmj.fly.domain.model.Result;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u001a\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\f0\u000bH&J\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000f0\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/dmj/fly/domain/repository/MediaRepository;", "", "deleteFile", "Lcom/dmj/fly/domain/model/Result;", "", "mediaFile", "Lcom/dmj/fly/domain/model/MediaFile;", "(Lcom/dmj/fly/domain/model/MediaFile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadFile", "", "getDownloadProgress", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Pair;", "", "refreshFileList", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface MediaRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object refreshFileList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<? extends java.util.List<com.dmj.fly.domain.model.MediaFile>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object downloadFile(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.MediaFile mediaFile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<java.lang.String>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteFile(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.MediaFile mediaFile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<kotlin.Pair<java.lang.String, java.lang.Integer>> getDownloadProgress();
}