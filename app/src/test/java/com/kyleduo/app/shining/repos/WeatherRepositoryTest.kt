package com.kyleduo.app.shining.repos

import com.kyleduo.app.shining.api.WeatherApi
import com.kyleduo.app.shining.datastore.MemCache
import com.kyleduo.app.shining.model.Weather
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyBlocking
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import java.util.*

/**
 * @author zhangduo on 2020/11/17
 */
class WeatherRepositoryTest : StringSpec({

    val weatherService: WeatherApi.WeatherService = mock {
        onBlocking { queryWeather(any()) }.thenReturn(null)
    }
    val memCache: MemCache = mock()

    val weatherRepository = WeatherRepository(weatherService, memCache)

    beforeEach {
        Mockito.clearInvocations(weatherService)
    }

    "When no cache, Then query through service" {
        whenever(memCache.get<Any>(any())).thenReturn(null)

        weatherRepository.queryWeather("1")
        verifyBlocking(weatherService) {
            queryWeather("1")
        }
    }

    "When has cache, Then do not query service And using cache" {
        val weather = Weather(updateTime = Date())
        whenever(memCache.get<Any>(any())).thenReturn(weather)

        val ret = weatherRepository.queryWeather("1")
        verifyBlocking(weatherService, mode = VerificationModeFactory.noMoreInteractions()) {
            queryWeather("1")
        }
        weather.shouldBeSameInstanceAs(ret)
    }
})