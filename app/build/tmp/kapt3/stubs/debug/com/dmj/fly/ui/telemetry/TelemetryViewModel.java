package com.dmj.fly.ui.telemetry;

import androidx.lifecycle.ViewModel;
import com.dmj.fly.domain.model.AircraftStatus;
import com.dmj.fly.domain.model.FlightTelemetry;
import com.dmj.fly.domain.repository.AircraftRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lcom/dmj/fly/ui/telemetry/TelemetryViewModel;", "Landroidx/lifecycle/ViewModel;", "aircraftRepository", "Lcom/dmj/fly/domain/repository/AircraftRepository;", "(Lcom/dmj/fly/domain/repository/AircraftRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dmj/fly/ui/telemetry/TelemetryUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "observeData", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TelemetryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.dmj.fly.domain.repository.AircraftRepository aircraftRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.dmj.fly.ui.telemetry.TelemetryUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.ui.telemetry.TelemetryUiState> uiState = null;
    
    @javax.inject.Inject()
    public TelemetryViewModel(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.domain.repository.AircraftRepository aircraftRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.dmj.fly.ui.telemetry.TelemetryUiState> getUiState() {
        return null;
    }
    
    private final void observeData() {
    }
}