package com.dmj.fly.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AircraftRepositoryImpl_Factory implements Factory<AircraftRepositoryImpl> {
  @Override
  public AircraftRepositoryImpl get() {
    return newInstance();
  }

  public static AircraftRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AircraftRepositoryImpl newInstance() {
    return new AircraftRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final AircraftRepositoryImpl_Factory INSTANCE = new AircraftRepositoryImpl_Factory();
  }
}
