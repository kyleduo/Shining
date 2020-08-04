package com.kyleduo.app.shining.di

import android.content.Context
import com.kyleduo.app.shining.weatherpage.di.WeatherPageComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
//        SubComponentModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun weatherPageComponent(): WeatherPageComponent.Factory
}

@Module(
    subcomponents = [
        WeatherPageComponent::class
    ]
)
class SubComponentModule