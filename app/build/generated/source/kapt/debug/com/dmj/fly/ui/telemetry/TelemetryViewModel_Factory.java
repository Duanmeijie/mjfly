package com.dmj.fly.ui.telemetry;

import com.dmj.fly.domain.repository.AircraftRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class TelemetryViewModel_Factory implements Factory<TelemetryViewModel> {
  private final Provider<AircraftRepository> aircraftRepositoryProvider;

  public TelemetryViewModel_Factory(Provider<AircraftRepository> aircraftRepositoryProvider) {
    this.aircraftRepositoryProvider = aircraftRepositoryProvider;
  }

  @Override
  public TelemetryViewModel get() {
    return newInstance(aircraftRepositoryProvider.get());
  }

  public static TelemetryViewModel_Factory create(
      Provider<AircraftRepository> aircraftRepositoryProvider) {
    return new TelemetryViewModel_Factory(aircraftRepositoryProvider);
  }

  public static TelemetryViewModel newInstance(AircraftRepository aircraftRepository) {
    return new TelemetryViewModel(aircraftRepository);
  }
}
