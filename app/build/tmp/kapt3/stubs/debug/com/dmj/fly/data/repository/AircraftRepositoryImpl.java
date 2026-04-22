package com.dmj.fly.data.repository;

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper;
import com.dmj.fly.domain.model.AircraftStatus;
import com.dmj.fly.domain.model.FlightTelemetry;
import com.dmj.fly.domain.repository.AircraftRepository;
import dji.sdk.keyvalue.key.FlightControllerKey;
import dji.sdk.keyvalue.key.KeyConnection;
import dji.sdk.keyvalue.value.flightcontroller.FlightMode;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u0016J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004H\u0016\u00a8\u0006\n"}, d2 = {"Lcom/dmj/fly/data/repository/AircraftRepositoryImpl;", "Lcom/dmj/fly/domain/repository/AircraftRepository;", "()V", "getAircraftStatus", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dmj/fly/domain/model/AircraftStatus;", "getConnectionState", "", "getTelemetry", "Lcom/dmj/fly/domain/model/FlightTelemetry;", "app_debug"})
public final class AircraftRepositoryImpl implements com.dmj.fly.domain.repository.AircraftRepository {
    
    @javax.inject.Inject
    public AircraftRepositoryImpl() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.AircraftStatus> getAircraftStatus() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.FlightTelemetry> getTelemetry() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> getConnectionState() {
        return null;
    }
}