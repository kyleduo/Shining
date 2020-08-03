package com.kyleduo.app.shining.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyleduo.app.shining.R
import com.kyleduo.app.shining.ShiningApp
import com.kyleduo.app.shining.repos.FavoriteCityRepository
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : AppCompatActivity() {

    @Suppress("UNCHECKED_CAST")
    private val viewModel: WeatherViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = WeatherViewModel(
                FavoriteCityRepository(
                    ShiningApp.app,
                    ShiningApp.app.gson,
                    ShiningApp.app.spDataStore
                )
            ) as T
        }
    })
    private lateinit var adapter: WeatherPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherPageAdapter(this)
        weather_pager.adapter = adapter

        viewModel.cities.observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            adapter.cities = it
        })

        viewModel.reload()
    }
}