package com.kyleduo.app.shining.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.kyleduo.app.shining.R
import com.kyleduo.app.shining.ShiningApp
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class WeatherActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Suppress("UNCHECKED_CAST")
    private val viewModel: WeatherViewModel by viewModels { factory }
    private lateinit var adapter: WeatherPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inject dependencies
        (application as ShiningApp).appComponent.weatherComponent().create().inject(this)

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