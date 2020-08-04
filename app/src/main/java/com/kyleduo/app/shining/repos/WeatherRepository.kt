package com.kyleduo.app.shining.repos

import com.kyleduo.app.shining.api.WeatherApi
import com.kyleduo.app.shining.datastore.MemCache
import com.kyleduo.app.shining.model.Weather
import java.util.*

class WeatherRepository(
    private val weatherService: WeatherApi.WeatherService,
    private val cache: MemCache
) {

    suspend fun queryWeather(cityId: String): Weather? {
        val cached = cache.get<Weather>(cityId)
        if (cached != null) {
            return cached
        }
        return weatherService.queryWeather(cityId)?.also {
            it.updateTime = Date()
            cache.setValue(cityId, it, 60 * 1000)
        }
    }
}