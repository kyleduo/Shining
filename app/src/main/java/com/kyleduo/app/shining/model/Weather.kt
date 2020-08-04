package com.kyleduo.app.shining.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author zhangduo on 2020/8/3
 */
data class Weather(
    @SerializedName("cityid")
    val cityId: String? = "--",
    @SerializedName("date")
    val date: String? = "--",
    @SerializedName("week")
    val week: String? = "--",
    @SerializedName("wea")
    val weather: String? = "--",
    @SerializedName("wea_img")
    val weatherImg: String? = "--",
    @SerializedName("tem")
    val tempRealtime: String? = "--",
    @SerializedName("tem1")
    val tempHigh: String? = "--",
    @SerializedName("tem2")
    val tempLow: String? = "--",
    @SerializedName("win")
    val wind: String? = "--",
    @SerializedName("win_speed")
    val windSpeed: String? = "--",
    @SerializedName("air_level")
    val airLevel: String? = "--",
    @SerializedName("air_pm25")
    val airPM25: String? = "--",
    @Expose(deserialize = false, serialize = false)
    var updateTime: Date?
)