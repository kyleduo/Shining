package com.kyleduo.app.shining.weatherpage

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kyleduo.app.shining.model.City
import com.kyleduo.app.shining.model.Weather
import com.kyleduo.app.shining.repos.WeatherRepository
import kotlinx.coroutines.launch

class WeatherPageViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val repo: WeatherRepository
) : ViewModel() {

    companion object {
        const val ARG_CITY = "city"
    }

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    private val city: City = savedStateHandle.get<City>(ARG_CITY) ?: throw IllegalStateException()

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