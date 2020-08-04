package com.kyleduo.app.shining.api

import com.kyleduo.app.shining.BuildConfig
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

    private const val Q_APP_ID = "appid"
    private const val Q_APP_SECRET = "appsecret"
    private const val BASE_URL = "https://tianqiapi.com/"

    fun createService(): WeatherService {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor {
                val origin = it.request()
                val newUrl =
                    origin.url().newBuilder().setQueryParameter(Q_APP_ID, BuildConfig.API_APP_ID)
                        .setQueryParameter(Q_APP_SECRET, BuildConfig.API_APP_SECRET).build()
                val newRequest = origin.newBuilder().url(newUrl).build()
                it.proceed(newRequest)
            }
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WeatherService::class.java)
    }

    interface WeatherService {
        @GET("api?version=v6")
        suspend fun queryWeather(@Query("cityid") cityId: String?): Weather?
    }
}
