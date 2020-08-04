package com.kyleduo.app.shining.di

import com.kyleduo.app.shining.api.WeatherApi
import com.kyleduo.app.shining.datastore.MemCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherApiService(): WeatherApi.WeatherService {
        return WeatherApi.createService()
    }

    @Singleton
    @Provides
    fun provideMemCache(): MemCache {
        return MemCache()
    }
}