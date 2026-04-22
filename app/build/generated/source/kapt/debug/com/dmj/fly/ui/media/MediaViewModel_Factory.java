package com.dmj.fly.ui.media;

import com.dmj.fly.domain.repository.MediaRepository;
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
public final class MediaViewModel_Factory implements Factory<MediaViewModel> {
  private final Provider<MediaRepository> mediaRepositoryProvider;

  public MediaViewModel_Factory(Provider<MediaRepository> mediaRepositoryProvider) {
    this.mediaRepositoryProvider = mediaRepositoryProvider;
  }

  @Override
  public MediaViewModel get() {
    return newInstance(mediaRepositoryProvider.get());
  }

  public static MediaViewModel_Factory create(Provider<MediaRepository> mediaRepositoryProvider) {
    return new MediaViewModel_Factory(mediaRepositoryProvider);
  }

  public static MediaViewModel newInstance(MediaRepository mediaRepository) {
    return new MediaViewModel(mediaRepository);
  }
}
