package com.kyleduo.app.shining.mock

import com.kyleduo.app.shining.model.City
import com.kyleduo.app.shining.repos.IFavoriteCityRepository

class MockFavoriteCityRepository : IFavoriteCityRepository {
    override suspend fun loadSelectedCities(): List<City> {
        return listOf(
            City(id = "101010100", name = "北京", pinyin = "beijing"),
            City(id = "101190101", name = "南京", pinyin = "nanjing"),
            City(id = "101044000", name = "南岸", pinyin = "nanan"),
            City(id = "101030800", name = "和平", pinyin = "heping"),
            City(id = "101120101", name = "济南", pinyin = "jinan")
        )
    }
}