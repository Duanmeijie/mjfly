package com.dmj.fly.ui.camera;

import androidx.lifecycle.ViewModel;
import com.dmj.fly.domain.model.CameraMode;
import com.dmj.fly.domain.model.CameraState;
import com.dmj.fly.domain.model.Result;
import com.dmj.fly.domain.repository.CameraRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\rJ\u0006\u0010\u0015\u001a\u00020\rJ\u0006\u0010\u0016\u001a\u00020\rJ\u0006\u0010\u0017\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0018"}, d2 = {"Lcom/dmj/fly/ui/camera/CameraViewModel;", "Landroidx/lifecycle/ViewModel;", "cameraRepository", "Lcom/dmj/fly/domain/repository/CameraRepository;", "(Lcom/dmj/fly/domain/repository/CameraRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dmj/fly/ui/camera/CameraUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "observeCameraState", "", "setMode", "mode", "Lcom/dmj/fly/domain/model/CameraMode;", "setZoom", "zoomFactor", "", "shootPhoto", "startRecord", "stopRecord", "toggleRecord", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CameraViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.dmj.fly.domain.repository.CameraRepository cameraRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.dmj.fly.ui.camera.CameraUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.ui.camera.CameraUiState> uiState = null;
    
    @javax.inject.Inject()
    public CameraViewModel(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.repository.CameraRepository cameraRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.ui.camera.CameraUiState> getUiState() {
        return null;
    }
    
    private final void observeCameraState() {
    }
    
    public final void setMode(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.CameraMode mode) {
    }
    
    public final void shootPhoto() {
    }
    
    public final void startRecord() {
    }
    
    public final void stopRecord() {
    }
    
    public final void toggleRecord() {
    }
    
    public final void setZoom(float zoomFactor) {
    }
}