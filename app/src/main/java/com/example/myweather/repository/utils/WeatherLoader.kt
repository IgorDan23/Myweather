package com.example.myweather.repository.utils

import android.os.Handler
import android.os.Looper
import com.example.myweather.repository.OnServerResponse
import com.example.myweather.repository.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponse: OnServerResponse) {
    fun loadWeather(lat: Double, lon: Double) {
        Thread {
            val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
            val uri = URL(urlText)
            val urlConnection: HttpsURLConnection =
                (uri.openConnection() as HttpsURLConnection).apply {
                    addRequestProperty("X-Yandex-API-Key", "a65c811d-e487-44d6-a8a0-5bb74573d5bf")
                }
            val headers = urlConnection.headerFields
            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))

            try {
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post { onServerResponse.onResponse(weatherDTO) }
            }catch (e:NumberFormatException){
                e.message
            }finally {
                urlConnection.disconnect()
            }


        }.start()

    }
}