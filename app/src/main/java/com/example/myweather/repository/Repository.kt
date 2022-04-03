package com.example.myweather.repository

interface Repository {
    fun getWeatherFromServer():List<Weather>
    fun getRussianWeatherFromLocalStorage():List<Weather>
    fun getWorldWeatherFromLocalStorage():List<Weather>
}