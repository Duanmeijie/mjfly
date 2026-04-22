package com.dmj.fly.ui.control;

import com.dmj.fly.domain.repository.AircraftRepository;
import com.dmj.fly.domain.repository.FlightControlRepository;
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
public final class ControlViewModel_Factory implements Factory<ControlViewModel> {
  private final Provider<FlightControlRepository> flightControlRepositoryProvider;

  private final Provider<AircraftRepository> aircraftRepositoryProvider;

  public ControlViewModel_Factory(Provider<FlightControlRepository> flightControlRepositoryProvider,
      Provider<AircraftRepository> aircraftRepositoryProvider) {
    this.flightControlRepositoryProvider = flightControlRepositoryProvider;
    this.aircraftRepositoryProvider = aircraftRepositoryProvider;
  }

  @Override
  public ControlViewModel get() {
    return newInstance(flightControlRepositoryProvider.get(), aircraftRepositoryProvider.get());
  }

  public static ControlViewModel_Factory create(
      Provider<FlightControlRepository> flightControlRepositoryProvider,
      Provider<AircraftRepository> aircraftRepositoryProvider) {
    return new ControlViewModel_Factory(flightControlRepositoryProvider, aircraftRepositoryProvider);
  }

  public static ControlViewModel newInstance(FlightControlRepository flightControlRepository,
      AircraftRepository aircraftRepository) {
    return new ControlViewModel(flightControlRepository, aircraftRepository);
  }
}
