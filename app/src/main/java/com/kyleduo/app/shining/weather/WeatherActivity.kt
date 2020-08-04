package com.kyleduo.app.shining.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.kyleduo.app.shining.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var adapter: WeatherPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherPageAdapter(this)
        weather_pager.adapter = adapter
        weather_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateCityName(position)
            }
        })

        viewModel.cities.observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            updateCityName(weather_pager.currentItem)
            adapter.cities = it
        })
    }

    override fun onStart() {
        super.onStart()

        viewModel.reload()
    }

    private fun updateCityName(pos: Int) {
        weather_city_name.text = viewModel.cities.value?.get(pos)?.name ?: "Unknown"
    }
}