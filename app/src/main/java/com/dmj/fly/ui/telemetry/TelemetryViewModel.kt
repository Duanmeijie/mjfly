package com.dmj.fly.ui.telemetry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmj.fly.domain.model.AircraftStatus
import com.dmj.fly.domain.model.FlightTelemetry
import com.dmj.fly.domain.repository.AircraftRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TelemetryUiState(
    val aircraftStatus: AircraftStatus = AircraftStatus(),
    val flightTelemetry: FlightTelemetry = FlightTelemetry()
)

@HiltViewModel
class TelemetryViewModel @Inject constructor(
    private val aircraftRepository: AircraftRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TelemetryUiState())
    val uiState: StateFlow<TelemetryUiState> = _uiState.asStateFlow()

    init {
        observeData()
    }

    private fun observeData() {
        viewModelScope.launch {
            combine(
                aircraftRepository.getAircraftStatus(),
                aircraftRepository.getTelemetry()
            ) { status, telemetry ->
                TelemetryUiState(status, telemetry)
            }.collect { state ->
                _uiState.value = state
            }
        }
    }
}