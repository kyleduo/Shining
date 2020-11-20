package com.kyleduo.app.shining.repos

import com.kyleduo.app.shining.api.WeatherApi
import com.kyleduo.app.shining.datastore.MemCache
import com.kyleduo.app.shining.model.Weather
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.*
import java.util.*

/**
 * @author zhangduo on 2020/11/17
 */
class WeatherRepositoryTest : StringSpec({

    val weatherService: WeatherApi.WeatherService = mockk(relaxed = true)
    val memCache: MemCache = mockk(relaxed = true)

    val weatherRepository = WeatherRepository(weatherService, memCache)

    beforeEach {
        clearMocks(weatherService)
        coEvery { weatherService.queryWeather(any()) }.returns(null)
    }

    "When no cache, Then query through service" {
        every { memCache.get<Any>(any()) }.returns(null)
        weatherRepository.queryWeather("1")
        coVerify { weatherService.queryWeather("1") }
    }

    "When has cache, Then do not query service And using cache" {
        val weather = Weather(updateTime = Date())
        every { memCache.get<Any>(any()) }.returns(weather)

        val ret = weatherRepository.queryWeather("1")

        coVerify(exactly = 0) { weatherService.queryWeather("1") }
        ret shouldBeSameInstanceAs weather
    }
})