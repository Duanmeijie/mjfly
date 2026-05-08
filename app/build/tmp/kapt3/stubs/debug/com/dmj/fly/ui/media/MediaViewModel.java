package com.dmj.fly.ui.media;

import androidx.lifecycle.ViewModel;
import com.dmj.fly.domain.model.MediaFile;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.repository.MediaRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\rJ\b\u0010\u0012\u001a\u00020\rH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0013"}, d2 = {"Lcom/dmj/fly/ui/media/MediaViewModel;", "Landroidx/lifecycle/ViewModel;", "mediaRepository", "Lcom/dmj/fly/domain/repository/MediaRepository;", "(Lcom/dmj/fly/domain/repository/MediaRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dmj/fly/ui/media/MediaUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteFile", "", "file", "Lcom/dmj/fly/domain/model/MediaFile;", "downloadFile", "loadFiles", "observeDownloadProgress", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MediaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.dmj.fly.domain.repository.MediaRepository mediaRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.dmj.fly.ui.media.MediaUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.ui.media.MediaUiState> uiState = null;
    
    @javax.inject.Inject()
    public MediaViewModel(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.repository.MediaRepository mediaRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.ui.media.MediaUiState> getUiState() {
        return null;
    }
    
    private final void observeDownloadProgress() {
    }
    
    public final void loadFiles() {
    }
    
    public final void downloadFile(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.MediaFile file) {
    }
    
    public final void deleteFile(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.MediaFile file) {
    }
}