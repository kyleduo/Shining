package com.kyleduo.app.shining.repos

import android.annotation.SuppressLint
import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kyleduo.app.shining.datastore.SPDataStore
import com.kyleduo.app.shining.model.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteCityRepository(
    private val app: Application,
    private val gson: Gson,
    private val sp: SPDataStore
) {
    companion object {
        const val KEY_FAVORITE_CITIES = "favorite_cities"
        const val NAME = "preferences"
    }

    suspend fun loadSelectedCities(): List<City> {
        return withContext(Dispatchers.IO) {
            return@withContext innerLoad()
        }
    }

    private fun innerLoad(): List<City> {
        val text = sp.getString(app, NAME, KEY_FAVORITE_CITIES, "")
        if (text.isNullOrEmpty()) {
            return listOf()
        }
        val type = object : TypeToken<List<City>>() {}.type
        return try {
            gson.fromJson<List<City>>(text, type)
        } catch (e: Exception) {
            listOf()
        }
    }

    @SuppressLint("ApplySharedPref")
    suspend fun saveCity(city: City) {
        withContext(Dispatchers.IO) {
            val origin = innerLoad()
            val previous = origin.firstOrNull {
                it.id == city.id
            }
            if (previous != null) {
                return@withContext
            }
            val cities = origin.toMutableList()
            cities.add(city)
            sp.saveString(app, NAME, KEY_FAVORITE_CITIES, gson.toJson(cities), commit = true)
        }
    }
}