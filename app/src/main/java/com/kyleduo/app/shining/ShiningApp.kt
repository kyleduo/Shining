package com.kyleduo.app.shining

import android.app.Application
import com.google.gson.Gson
import com.kyleduo.app.shining.datastore.MemCache
import com.kyleduo.app.shining.datastore.SPDataStore

/**
 * @author zhangduo on 2020/8/3
 */
class ShiningApp : Application() {
    val spDataStore: SPDataStore by lazy { SPDataStore() }
    val gson: Gson by lazy { Gson() }
    val cache: MemCache by lazy { MemCache() }

    companion object {
        lateinit var app: ShiningApp
    }

    override fun onCreate() {
        super.onCreate()

        app = this
    }
}
