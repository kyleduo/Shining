package com.kyleduo.app.shining.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kyleduo.app.shining.R
import com.kyleduo.app.shining.model.Weather

/**
 * @author zhangduo on 2020/8/3
 */
class WeatherPageAdapter : RecyclerView.Adapter<WeatherPageAdapter.WeatherPageViewHolder>() {

    var data = listOf<Weather>()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherPageViewHolder {
        return WeatherPageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_weather_page, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherPageViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    class WeatherPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv = itemView.findViewById<TextView>(R.id.tv_weather_detail)

        fun bindData(weather: Weather) {
            tv.text = weather.toString()
        }
    }
}
