package com.kyleduo.app.shining.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyleduo.app.shining.model.City
import com.kyleduo.app.shining.repos.IFavoriteCityRepository
import kotlinx.coroutines.launch

/**
 * @author zhangduo on 2020/8/3
 */
class WeatherViewModel(private val repo: IFavoriteCityRepository) : ViewModel() {
    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    fun reload() {
        viewModelScope.launch {
            val cities = repo.loadSelectedCities()
            _cities.value = if (cities.isEmpty()) {
                listOf(City.defaultCity())
            } else {
                cities
            }
        }
    }
}
