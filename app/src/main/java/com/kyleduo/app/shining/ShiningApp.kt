package com.kyleduo.app.shining

import android.app.Application
import com.kyleduo.app.shining.di.AppComponent
import com.kyleduo.app.shining.di.DaggerAppComponent

/**
 * @author zhangduo on 2020/8/3
 */
class ShiningApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}
