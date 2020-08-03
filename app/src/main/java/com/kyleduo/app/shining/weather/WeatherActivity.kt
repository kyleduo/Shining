package com.kyleduo.app.shining.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kyleduo.app.shining.R
import com.kyleduo.app.shining.api.WeatherApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            try {
                val weather = WeatherApi.service.queryWeather("101010100") ?: return@launch
                demo_text.text = weather.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}