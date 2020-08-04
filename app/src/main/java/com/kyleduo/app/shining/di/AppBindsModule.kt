package com.kyleduo.app.shining.di

import com.kyleduo.app.shining.mock.MockFavoriteCityRepository
import com.kyleduo.app.shining.repos.IFavoriteCityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppBindsModule {

    @Singleton
    @Binds
    abstract fun bindIFavoriteCityRepository(
        mockFavoriteCityRepository: MockFavoriteCityRepository
    ): IFavoriteCityRepository
}