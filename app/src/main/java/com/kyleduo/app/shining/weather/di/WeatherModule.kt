package com.kyleduo.app.shining.weather.di

import androidx.lifecycle.ViewModel
import com.kyleduo.app.shining.di.ViewModelKey
import com.kyleduo.app.shining.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeatherModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindWeatherViewModel(weatherViewModel: WeatherViewModel): ViewModel
}