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
public final class CameraRepositoryImpl_Factory implements Factory<CameraRepositoryImpl> {
  @Override
  public CameraRepositoryImpl get() {
    return newInstance();
  }

  public static CameraRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CameraRepositoryImpl newInstance() {
    return new CameraRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final CameraRepositoryImpl_Factory INSTANCE = new CameraRepositoryImpl_Factory();
  }
}
