package com.example.myweather.repository

fun interface OnServerResponse {
    fun onResponse(weatherDTO: WeatherDTO)
}