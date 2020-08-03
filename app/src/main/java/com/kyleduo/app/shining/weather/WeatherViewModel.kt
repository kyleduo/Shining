package com.kyleduo.app.shining.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyleduo.app.shining.model.City

/**
 * @author zhangduo on 2020/8/3
 */
class WeatherViewModel : ViewModel() {
    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    fun reload() {
        _cities.value =
            listOf(
                City("101010100", "北京", "beijing"),
                City("101040100", "重庆", "chongqing")
            )
    }
}
