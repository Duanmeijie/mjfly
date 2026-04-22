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
public final class LiveStreamRepositoryImpl_Factory implements Factory<LiveStreamRepositoryImpl> {
  @Override
  public LiveStreamRepositoryImpl get() {
    return newInstance();
  }

  public static LiveStreamRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LiveStreamRepositoryImpl newInstance() {
    return new LiveStreamRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final LiveStreamRepositoryImpl_Factory INSTANCE = new LiveStreamRepositoryImpl_Factory();
  }
}
