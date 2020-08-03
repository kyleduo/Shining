package com.kyleduo.app.shining.weatherpage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyleduo.app.shining.R
import com.kyleduo.app.shining.model.City
import kotlinx.android.synthetic.main.fragment_weather_page.*

class WeatherPageFragment : Fragment(R.layout.fragment_weather_page) {

    companion object {
        const val ARG_CITY = "city"

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
                return WeatherPageViewModel(city) as T
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
            tv_weather_detail.text = it.toString()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.refresh()
    }
}