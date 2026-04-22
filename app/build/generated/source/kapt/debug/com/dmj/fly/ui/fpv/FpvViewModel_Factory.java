package com.dmj.fly.ui.fpv;

import com.dmj.fly.domain.repository.CameraRepository;
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
public final class FpvViewModel_Factory implements Factory<FpvViewModel> {
  private final Provider<CameraRepository> cameraRepositoryProvider;

  private final Provider<FlightControlRepository> flightControlRepositoryProvider;

  public FpvViewModel_Factory(Provider<CameraRepository> cameraRepositoryProvider,
      Provider<FlightControlRepository> flightControlRepositoryProvider) {
    this.cameraRepositoryProvider = cameraRepositoryProvider;
    this.flightControlRepositoryProvider = flightControlRepositoryProvider;
  }

  @Override
  public FpvViewModel get() {
    return newInstance(cameraRepositoryProvider.get(), flightControlRepositoryProvider.get());
  }

  public static FpvViewModel_Factory create(Provider<CameraRepository> cameraRepositoryProvider,
      Provider<FlightControlRepository> flightControlRepositoryProvider) {
    return new FpvViewModel_Factory(cameraRepositoryProvider, flightControlRepositoryProvider);
  }

  public static FpvViewModel newInstance(CameraRepository cameraRepository,
      FlightControlRepository flightControlRepository) {
    return new FpvViewModel(cameraRepository, flightControlRepository);
  }
}
