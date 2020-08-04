package com.kyleduo.app.shining.repos

import com.kyleduo.app.shining.model.City

interface IFavoriteCityRepository {

    suspend fun loadSelectedCities(): List<City>
}