package com.dmj.fly.di

import com.dmj.fly.data.repository.AircraftRepositoryImpl
import com.dmj.fly.data.repository.CameraRepositoryImpl
import com.dmj.fly.data.repository.FlightControlRepositoryImpl
import com.dmj.fly.data.repository.LiveStreamRepositoryImpl
import com.dmj.fly.data.repository.MediaRepositoryImpl
import com.dmj.fly.domain.repository.AircraftRepository
import com.dmj.fly.domain.repository.CameraRepository
import com.dmj.fly.domain.repository.FlightControlRepository
import com.dmj.fly.domain.repository.LiveStreamRepository
import com.dmj.fly.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAircraftRepository(impl: AircraftRepositoryImpl): AircraftRepository

    @Binds
    @Singleton
    abstract fun bindFlightControlRepository(impl: FlightControlRepositoryImpl): FlightControlRepository

    @Binds
    @Singleton
    abstract fun bindCameraRepository(impl: CameraRepositoryImpl): CameraRepository

    @Binds
    @Singleton
    abstract fun bindMediaRepository(impl: MediaRepositoryImpl): MediaRepository

    @Binds
    @Singleton
    abstract fun bindLiveStreamRepository(impl: LiveStreamRepositoryImpl): LiveStreamRepository
}