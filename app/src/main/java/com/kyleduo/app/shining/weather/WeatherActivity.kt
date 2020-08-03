package com.kyleduo.app.shining.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kyleduo.app.shining.R
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
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

//        lifecycleScope.launch {
//            try {
//                val weather = WeatherApi.service.queryWeather("101010100") ?: return@launch
//                adapter.cities = listOf(weather, weather)
//                adapter.notifyDataSetChanged()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }

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