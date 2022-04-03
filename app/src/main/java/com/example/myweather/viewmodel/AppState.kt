package com.example.myweather.viewmodel

import com.example.myweather.repository.Weather
import java.lang.Exception

sealed class AppState {
    object Loadind:AppState()
    data class Success(val weatherList:List<Weather>):AppState()

    data class Error(val error:Throwable):AppState()
}