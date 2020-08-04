package com.kyleduo.app.shining.weatherpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyleduo.app.shining.model.City
import com.kyleduo.app.shining.model.Weather
import com.kyleduo.app.shining.repos.WeatherRepository
import kotlinx.coroutines.launch

class WeatherPageViewModel(
    private val city: City,
    private val repo: WeatherRepository
) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    fun refresh() {
        viewModelScope.launch {
            try {
                val w = repo.queryWeather(city.id) ?: return@launch
                _weather.value = w
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}