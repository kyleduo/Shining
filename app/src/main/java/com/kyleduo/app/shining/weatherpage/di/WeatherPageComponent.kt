package com.kyleduo.app.shining.weatherpage.di

import com.kyleduo.app.shining.model.City
import com.kyleduo.app.shining.weatherpage.WeatherPageFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        WeatherPageModule::class
    ]
)
interface WeatherPageComponent {

    @Subcomponent.Factory
    interface Factory {
        /**
         * 创建 WeatherPageComponent 时传入 city。
         * 使用 @BindsInstance 声明，Dagger 将构造 Factory<City> 实例，在 Component 作用域内提供city实例。
         */
        fun create(@BindsInstance city: City): WeatherPageComponent
    }

    fun inject(weatherPageFragment: WeatherPageFragment)
}