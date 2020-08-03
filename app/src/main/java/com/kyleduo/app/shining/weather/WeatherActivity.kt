package com.kyleduo.app.shining.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kyleduo.app.shining.R
import com.kyleduo.app.shining.api.WeatherApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {

    val viewModel: WeatherViewModel by viewModels()
    lateinit var adapter: WeatherPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherPageAdapter()
        weather_pager.adapter = adapter

        lifecycleScope.launch {
            try {
                val weather = WeatherApi.service.queryWeather("101010100") ?: return@launch
                adapter.data = listOf(weather, weather)
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

//        viewModel.cities.observe(this, Observer {
//            if (it == null) {
//                return@Observer
//            }
//            val ret = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
//                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//                    return data[oldItemPosition] == it[newItemPosition]
//                }
//
//                override fun getOldListSize(): Int = data.size
//
//                override fun getNewListSize(): Int = it.size
//
//                override fun areContentsTheSame(
//                        oldItemPosition: Int,
//                        newItemPosition: Int
//                ): Boolean {
//                    return data[oldItemPosition] == it[newItemPosition]
//                }
//
//            })
//            ret.dispatchUpdatesTo(this)
//        })

    }
}