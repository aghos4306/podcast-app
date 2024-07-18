package com.aghogho.podcastandroidapp.di

import com.aghogho.podcastandroidapp.data.remote.PodcastApiService
import com.aghogho.podcastandroidapp.data.repository_impl.PodcastApiServiceRepositoryImpl
import com.aghogho.podcastandroidapp.domain.repository.PodcastApiServiceRepository
import com.aghogho.podcastandroidapp.util.Constants.PODCAST_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePodcastApiService(): PodcastApiService {
        return Retrofit.Builder()
            .baseUrl(PODCAST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PodcastApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePodcastApiServiceRepository(podCastApi: PodcastApiService): PodcastApiServiceRepository {
        return PodcastApiServiceRepositoryImpl(podCastApi)
    }
}
