package com.kyleduo.app.shining.di

import android.content.Context
import com.kyleduo.app.shining.weather.di.WeatherComponent
import com.kyleduo.app.shining.weatherpage.di.WeatherPageComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppBindsModule::class,
        SubComponentModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun weatherPageComponent(): WeatherPageComponent.Factory
    fun weatherComponent(): WeatherComponent.Factory
}

@Module(
    subcomponents = [
        WeatherComponent::class,
        WeatherPageComponent::class
    ]
)
object SubComponentModule