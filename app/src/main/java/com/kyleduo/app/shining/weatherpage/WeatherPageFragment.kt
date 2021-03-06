package com.kyleduo.app.shining.weatherpage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyleduo.app.shining.R
import com.kyleduo.app.shining.ShiningApp
import com.kyleduo.app.shining.api.WeatherApi
import com.kyleduo.app.shining.api.iconResForWeather
import com.kyleduo.app.shining.model.City
import com.kyleduo.app.shining.model.Weather
import com.kyleduo.app.shining.repos.WeatherRepository
import com.kyleduo.app.shining.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_weather_page.*

class WeatherPageFragment : Fragment(R.layout.fragment_weather_page) {

    companion object {
        const val ARG_CITY = "city"
        const val PLACEHOLDER = "--"

        fun newInstance(city: City): WeatherPageFragment {
            val args = Bundle()
            args.putParcelable(ARG_CITY, city)

            val fragment = WeatherPageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var city: City

    @Suppress("UNCHECKED_CAST")
    private val viewModel: WeatherPageViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WeatherPageViewModel(
                    city,
                    WeatherRepository(WeatherApi.service, ShiningApp.app.cache)
                ) as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        city = arguments?.getParcelable(ARG_CITY) ?: return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weather.observe(viewLifecycleOwner, Observer {
            println(it.toString())
            updateWeather(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.refresh()
    }

    @SuppressLint("SetTextI18n")
    private fun updateWeather(weather: Weather) {
        fun String?.safe(): String {
            if (this.isNullOrEmpty()) {
                return PLACEHOLDER
            }
            return this
        }

        weather_page_curr_temp.text = weather.tempRealtime.safe()
        weather_page_update_time.text =
            getString(R.string.weather_update_time, DateUtils.formatQualified(weather.updateTime))
        weather_page_icon.setImageResource(iconResForWeather(weather.weatherImg ?: ""))
        weather_page_desc.text = weather.weather

        weather_page_detail_value_temp_high.text = weather.tempHigh.safe()
        weather_page_detail_value_wind.text =
            "${weather.wind.safe()} ${weather.windSpeed.safe()}"
        weather_page_detail_value_air_quality.text = weather.airLevel.safe()
        weather_page_detail_value_pm_2_5.text = weather.airPM25.safe()
    }
}