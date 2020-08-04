package com.kyleduo.app.shining.di

import com.kyleduo.app.shining.mock.MockFavoriteCityRepository
import com.kyleduo.app.shining.repos.IFavoriteCityRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * 提供绑定的Module一定是abstract的，dagger要求abstract的module不能包含非abstract的方法，所以需要单独创建abstract的module
 */
@Module
abstract class AppBindsModule {

    @Binds
    @Singleton
    abstract fun bindIFavoriteCityRepository(mockFavoriteCityRepository: MockFavoriteCityRepository): IFavoriteCityRepository
}