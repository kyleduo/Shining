package com.kyleduo.app.shining.api

import com.kyleduo.app.shining.R

fun iconResForWeather(weather: String): Int {
    return when (weather) {
        "yun" -> R.drawable.ic_weather_partly_cloud
        "qing" -> R.drawable.ic_weather_sunny
        "yu" -> R.drawable.ic_weather_rain
        "lei" -> R.drawable.ic_weather_thunder
        "wu" -> R.drawable.ic_weather_fog
        "bingbao" -> R.drawable.ic_weather_hail
        "xue" -> R.drawable.ic_weather_snow
        "yin" -> R.drawable.ic_weather_cloudy
        "shachen" -> R.drawable.ic_weather_dust
        else -> R.drawable.ic_weather_sunny
    }
}