package com.dmj.fly.domain.repository;

import com.dmj.fly.domain.model.AircraftStatus;
import com.dmj.fly.domain.model.FlightTelemetry;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H&\u00a8\u0006\t"}, d2 = {"Lcom/dmj/fly/domain/repository/AircraftRepository;", "", "getAircraftStatus", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dmj/fly/domain/model/AircraftStatus;", "getConnectionState", "", "getTelemetry", "Lcom/dmj/fly/domain/model/FlightTelemetry;", "app_debug"})
public abstract interface AircraftRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.AircraftStatus> getAircraftStatus();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.FlightTelemetry> getTelemetry();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> getConnectionState();
}