package com.dmj.fly.data.repository;

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper;
import com.dmj.fly.domain.model.AircraftStatus;
import com.dmj.fly.domain.model.FlightTelemetry;
import com.dmj.fly.domain.repository.AircraftRepository;
import dji.sdk.keyvalue.value.common.Attitude;
import dji.sdk.keyvalue.value.common.LocationCoordinate3D;
import dji.sdk.keyvalue.value.common.Velocity3D;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0016H\u0016J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\tJ\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000bJ\u000e\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\rJ\u001e\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/dmj/fly/data/repository/AircraftRepositoryImpl;", "Lcom/dmj/fly/domain/repository/AircraftRepository;", "keyManagerHelper", "Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;", "(Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;)V", "_mockAltitude", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_mockBattery", "", "_mockConnectionState", "", "_mockFlightMode", "", "_mockFlightTime", "", "_mockGpsSignal", "_mockIsFlying", "_mockLocation", "Ldji/sdk/keyvalue/value/common/LocationCoordinate3D;", "_mockMotorsOn", "getAircraftStatus", "Lkotlinx/coroutines/flow/Flow;", "Lcom/dmj/fly/domain/model/AircraftStatus;", "getConnectionState", "getTelemetry", "Lcom/dmj/fly/domain/model/FlightTelemetry;", "updateBattery", "", "percentage", "updateConnectionState", "connected", "updateFlightMode", "mode", "updateLocation", "lat", "lon", "alt", "app_debug"})
public final class AircraftRepositoryImpl implements com.dmj.fly.domain.repository.AircraftRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.dmj.fly.data.datasource.msdk.KeyManagerHelper keyManagerHelper = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _mockConnectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _mockFlightMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _mockGpsSignal = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _mockBattery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _mockIsFlying = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _mockMotorsOn = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _mockFlightTime = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<dji.sdk.keyvalue.value.common.LocationCoordinate3D> _mockLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Double> _mockAltitude = null;
    
    @javax.inject.Inject()
    public AircraftRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.data.datasource.msdk.KeyManagerHelper keyManagerHelper) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.AircraftStatus> getAircraftStatus() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.dmj.fly.domain.model.FlightTelemetry> getTelemetry() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> getConnectionState() {
        return null;
    }
    
    public final void updateConnectionState(boolean connected) {
    }
    
    public final void updateFlightMode(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    public final void updateBattery(int percentage) {
    }
    
    public final void updateLocation(double lat, double lon, double alt) {
    }
}