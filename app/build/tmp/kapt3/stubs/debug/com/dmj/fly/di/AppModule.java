package com.dmj.fly.di;

import com.dmj.fly.data.repository.AircraftRepositoryImpl;
import com.dmj.fly.data.repository.CameraRepositoryImpl;
import com.dmj.fly.data.repository.FlightControlRepositoryImpl;
import com.dmj.fly.data.repository.LiveStreamRepositoryImpl;
import com.dmj.fly.data.repository.MediaRepositoryImpl;
import com.dmj.fly.domain.repository.AircraftRepository;
import com.dmj.fly.domain.repository.CameraRepository;
import com.dmj.fly.domain.repository.FlightControlRepository;
import com.dmj.fly.domain.repository.LiveStreamRepository;
import com.dmj.fly.domain.repository.MediaRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/dmj/fly/di/AppModule;", "", "()V", "bindAircraftRepository", "Lcom/dmj/fly/domain/repository/AircraftRepository;", "impl", "Lcom/dmj/fly/data/repository/AircraftRepositoryImpl;", "bindCameraRepository", "Lcom/dmj/fly/domain/repository/CameraRepository;", "Lcom/dmj/fly/data/repository/CameraRepositoryImpl;", "bindFlightControlRepository", "Lcom/dmj/fly/domain/repository/FlightControlRepository;", "Lcom/dmj/fly/data/repository/FlightControlRepositoryImpl;", "bindLiveStreamRepository", "Lcom/dmj/fly/domain/repository/LiveStreamRepository;", "Lcom/dmj/fly/data/repository/LiveStreamRepositoryImpl;", "bindMediaRepository", "Lcom/dmj/fly/domain/repository/MediaRepository;", "Lcom/dmj/fly/data/repository/MediaRepositoryImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class AppModule {
    
    public AppModule() {
        super();
    }
    
    @dagger.Binds
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public abstract com.dmj.fly.domain.repository.AircraftRepository bindAircraftRepository(@org.jetbrains.annotations.NotNull
    com.dmj.fly.data.repository.AircraftRepositoryImpl impl);
    
    @dagger.Binds
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public abstract com.dmj.fly.domain.repository.FlightControlRepository bindFlightControlRepository(@org.jetbrains.annotations.NotNull
    com.dmj.fly.data.repository.FlightControlRepositoryImpl impl);
    
    @dagger.Binds
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public abstract com.dmj.fly.domain.repository.CameraRepository bindCameraRepository(@org.jetbrains.annotations.NotNull
    com.dmj.fly.data.repository.CameraRepositoryImpl impl);
    
    @dagger.Binds
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public abstract com.dmj.fly.domain.repository.MediaRepository bindMediaRepository(@org.jetbrains.annotations.NotNull
    com.dmj.fly.data.repository.MediaRepositoryImpl impl);
    
    @dagger.Binds
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public abstract com.dmj.fly.domain.repository.LiveStreamRepository bindLiveStreamRepository(@org.jetbrains.annotations.NotNull
    com.dmj.fly.data.repository.LiveStreamRepositoryImpl impl);
}