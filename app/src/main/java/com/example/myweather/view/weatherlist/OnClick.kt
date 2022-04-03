package com.example.myweather.view.weatherlist

import android.view.View
import com.example.myweather.repository.Weather

interface OnClick {
    fun OnItemClick(weather: Weather)
}