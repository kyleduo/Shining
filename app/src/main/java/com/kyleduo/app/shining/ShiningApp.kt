package com.kyleduo.app.shining

import android.app.Application

/**
 * @author zhangduo on 2020/8/3
 */
class ShiningApp : Application() {

    companion object {
        lateinit var app: ShiningApp
    }

    override fun onCreate() {
        super.onCreate()

        app = this
    }

}
