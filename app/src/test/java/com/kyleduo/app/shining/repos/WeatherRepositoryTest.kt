package com.kyleduo.app.shining.repos

import com.kyleduo.app.shining.api.WeatherApi
import com.kyleduo.app.shining.datastore.MemCache
import com.kyleduo.app.shining.model.Weather
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe
import java.util.*


/**
 * @author zhangduo on 2020/11/17
 */
@ExperimentalCoroutinesApi
object WeatherRepositoryTest : Spek({

    val weatherService: WeatherApi.WeatherService = mockk(relaxed = true)
    val memCache: MemCache = mockk(relaxed = true)

    lateinit var weatherRepository: WeatherRepository

    beforeEachGroup {
        weatherRepository = WeatherRepository(
            weatherService, memCache
        )
    }

    beforeEachTest {
        clearMocks(weatherService)
        coEvery { weatherService.queryWeather(any()) }.returns(null)
    }

    group("when no cache") {

        beforeEachTest {
            every { memCache.get<Any>(any()) }.returns(null)
        }

        describe("query weather") {

            beforeEachTest {
                runBlockingTest {
                    weatherRepository.queryWeather("1")
                }
            }

            it("should request through weather service") {
                runBlockingTest {
                    coVerify { weatherService.queryWeather("1") }
                }
            }
        }
    }

    group("when has cache") {
        val weather: Weather by memoized(mode = CachingMode.EACH_GROUP) { Weather(updateTime = Date()) }

        beforeEachTest {
            every { memCache.get<Any>(any()) }.returns(weather)
        }

        describe("query weather") {
            // 这里不能直接写调用

            lateinit var ret: Weather

            beforeEachTest {
                runBlockingTest {
                    ret = weatherRepository.queryWeather("1") as Weather
                }
            }

            it("should not perform web request") {
                runBlockingTest {
                    coVerify(exactly = 0) { weatherService.queryWeather(any()) }
                }
            }

            it("should use cache") {
                assert(ret === weather)
            }
        }
    }
})