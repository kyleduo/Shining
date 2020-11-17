package com.kyleduo.app.shining.repos

import com.kyleduo.app.shining.api.WeatherApi
import com.kyleduo.app.shining.datastore.MemCache
import com.kyleduo.app.shining.model.Weather
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*


/**
 * @author zhangduo on 2020/11/17
 */
@ExperimentalCoroutinesApi
internal class WeatherRepositoryTest {

    @MockK(relaxed = true)
    private lateinit var weatherService: WeatherApi.WeatherService

    @MockK(relaxed = true)
    private lateinit var memCache: MemCache

    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        weatherRepository = WeatherRepository(
            weatherService, memCache
        )

        coEvery { weatherService.queryWeather(any()) }.returns(null)
    }

    @Test
    fun `When no cache Then query through weather service`() = runBlockingTest {
        every { memCache.get<Any>(any()) }.returns(null)

        weatherRepository.queryWeather("1")

        coVerify { weatherService.queryWeather("1") }
    }

    @Test
    fun `When cache found Then do not perform http request`() = runBlockingTest {
        every { memCache.get<Any>(any()) }.returns(Weather(updateTime = Date()))

        weatherRepository.queryWeather("1")

        coVerify(exactly = 0) { weatherService.queryWeather(any()) }
    }

    @Test
    fun `When cache found Then return cached value`() = runBlockingTest {
        val weather = Weather(updateTime = Date())
        every { memCache.get<Any>(any()) }.returns(weather)

        val ret = weatherRepository.queryWeather("1")

        Assert.assertTrue(weather === ret)
    }
}