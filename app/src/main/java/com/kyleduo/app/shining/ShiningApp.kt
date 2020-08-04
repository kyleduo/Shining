package com.kyleduo.app.shining

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kyleduo.app.shining.datastore.MemCache
import com.kyleduo.app.shining.datastore.SPDataStore
import com.kyleduo.app.shining.di.AppComponent
import com.kyleduo.app.shining.di.DaggerAppComponent
import com.kyleduo.app.shining.utils.StringNullToEmptyAdapterFactory

/**
 * @author zhangduo on 2020/8/3
 */
class ShiningApp : Application() {
    val spDataStore: SPDataStore by lazy { SPDataStore() }
    val gson: Gson by lazy {
        GsonBuilder()
            .registerTypeAdapterFactory(StringNullToEmptyAdapterFactory())
            .create()
    }
    val cache: MemCache by lazy { MemCache() }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var app: ShiningApp
    }

    override fun onCreate() {
        super.onCreate()

        app = this
    }
}
