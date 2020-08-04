package com.kyleduo.app.shining.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import kotlin.reflect.KClass

@Module
abstract class ViewModelFactoryModule {

    /**
     * 将 实现 绑定到 接口
     * 当需要 ViewModelProvider.Factory 实例的时候，提供 ViewModelFactory 实例
     */
    @Binds
    abstract fun viewModelFactory(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory
}


@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
