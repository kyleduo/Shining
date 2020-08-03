package com.kyleduo.app.shining.api

import com.kyleduo.app.shining.model.Weather
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author zhangduo on 2020/8/3
 */
object WeatherApi {

    val service: WeatherService = createService()

    private fun createService(): WeatherService {
        val httpClient = OkHttpClient.Builder()
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://tianqiapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WeatherService::class.java)
    }

    interface WeatherService {
        @GET("api?version=v6&appid=22798437&appsecret=uena1xpw")
        suspend fun queryWeather(@Query("cityid") cityId: String?): Weather?
    }
}
