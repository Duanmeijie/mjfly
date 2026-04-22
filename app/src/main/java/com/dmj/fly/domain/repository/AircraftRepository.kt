package com.dmj.fly.domain.repository

import com.dmj.fly.domain.model.AircraftStatus
import com.dmj.fly.domain.model.FlightTelemetry
import kotlinx.coroutines.flow.Flow

interface AircraftRepository {
    fun getAircraftStatus(): Flow<AircraftStatus>
    fun getTelemetry(): Flow<FlightTelemetry>
    fun getConnectionState(): Flow<Boolean>
}