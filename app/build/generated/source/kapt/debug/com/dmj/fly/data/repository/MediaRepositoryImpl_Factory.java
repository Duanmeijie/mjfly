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
public final class MediaRepositoryImpl_Factory implements Factory<MediaRepositoryImpl> {
  @Override
  public MediaRepositoryImpl get() {
    return newInstance();
  }

  public static MediaRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MediaRepositoryImpl newInstance() {
    return new MediaRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final MediaRepositoryImpl_Factory INSTANCE = new MediaRepositoryImpl_Factory();
  }
}
