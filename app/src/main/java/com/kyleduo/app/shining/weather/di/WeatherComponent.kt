package com.kyleduo.app.shining.weather.di

import com.kyleduo.app.shining.weather.WeatherActivity
import dagger.Subcomponent

@Subcomponent(
    modules = [
        WeatherModule::class
    ]
)
interface WeatherComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherComponent
    }

    fun inject(weatherActivity: WeatherActivity)
}