package com.kyleduo.app.shining.weatherpage.di

import androidx.lifecycle.ViewModel
import com.kyleduo.app.shining.di.ViewModelKey
import com.kyleduo.app.shining.weatherpage.WeatherPageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeatherPageModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherPageViewModel::class)
    abstract fun weatherPageViewModel(weatherPageViewModel: WeatherPageViewModel): ViewModel
}