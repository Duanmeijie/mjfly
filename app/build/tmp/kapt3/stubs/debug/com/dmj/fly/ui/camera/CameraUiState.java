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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lcom/dmj/fly/ui/camera/CameraUiState;", "", "cameraState", "Lcom/dmj/fly/domain/model/CameraState;", "isRecording", "", "zoomMin", "", "zoomMax", "currentZoom", "(Lcom/dmj/fly/domain/model/CameraState;ZFFF)V", "getCameraState", "()Lcom/dmj/fly/domain/model/CameraState;", "getCurrentZoom", "()F", "()Z", "getZoomMax", "getZoomMin", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class CameraUiState {
    @org.jetbrains.annotations.NotNull()
    private final com.dmj.fly.domain.model.CameraState cameraState = null;
    private final boolean isRecording = false;
    private final float zoomMin = 0.0F;
    private final float zoomMax = 0.0F;
    private final float currentZoom = 0.0F;
    
    public CameraUiState(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.CameraState cameraState, boolean isRecording, float zoomMin, float zoomMax, float currentZoom) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dmj.fly.domain.model.CameraState getCameraState() {
        return null;
    }
    
    public final boolean isRecording() {
        return false;
    }
    
    public final float getZoomMin() {
        return 0.0F;
    }
    
    public final float getZoomMax() {
        return 0.0F;
    }
    
    public final float getCurrentZoom() {
        return 0.0F;
    }
    
    public CameraUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dmj.fly.domain.model.CameraState component1() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dmj.fly.ui.camera.CameraUiState copy(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.model.CameraState cameraState, boolean isRecording, float zoomMin, float zoomMax, float currentZoom) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}