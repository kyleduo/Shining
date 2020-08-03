package com.kyleduo.app.shining.weather

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kyleduo.app.shining.model.City
import com.kyleduo.app.shining.weatherpage.WeatherPageFragment

/**
 * @author zhangduo on 2020/8/3
 */
class WeatherPageAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    var cities = listOf<City>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = cities.size

    override fun createFragment(position: Int): Fragment {
        return WeatherPageFragment.newInstance(cities[position])
    }
}
