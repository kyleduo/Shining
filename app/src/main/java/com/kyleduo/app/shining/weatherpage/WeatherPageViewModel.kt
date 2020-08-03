package com.kyleduo.app.shining.weatherpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyleduo.app.shining.api.WeatherApi
import com.kyleduo.app.shining.model.City
import com.kyleduo.app.shining.model.Weather
import kotlinx.coroutines.launch

class WeatherPageViewModel(private val city: City) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    fun refresh() {
        viewModelScope.launch {
            try {
                val w = WeatherApi.service.queryWeather(city.id) ?: return@launch
                _weather.value = w
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}