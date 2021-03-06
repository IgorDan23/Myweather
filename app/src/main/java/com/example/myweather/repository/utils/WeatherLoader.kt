package com.example.myweather.repository.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.myweather.BuildConfig
import com.example.myweather.MainActivity
import com.example.myweather.repository.OnServerResponse
import com.example.myweather.repository.SnackbarResp
import com.example.myweather.repository.WeatherDTO
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.lang.Thread.sleep
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(
    private val onServerResponse: OnServerResponse,
    private val snackbarResp: SnackbarResp

) {
    fun loadWeather(lat: Double, lon: Double) {
        Thread {
            val urlText = "$DOMAIN{$PATH}lat=$lat&lon=$lon"
            val uri = URL(urlText)
            val urlConnection: HttpURLConnection =
                (uri.openConnection() as HttpURLConnection).apply {
                    addRequestProperty(API_KEY ,BuildConfig.WEATHER_API_KEY )
                }
            val responseCode = urlConnection.responseCode
            val serverside = 500..599
            val clientside = 400..499
            if (responseCode in serverside) {
                snackbarResp.OnRespsnackbar("Oшибка на сервере")
                sleep(500)
            } else if (responseCode in clientside) {
                snackbarResp.OnRespsnackbar("Что-то пошло не так у клиента")
                sleep(500)
            }
            val headers = urlConnection.headerFields
            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))

            try {
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)

                Handler(Looper.getMainLooper()).post {
                    onServerResponse.onResponse(weatherDTO)
                }
            } catch (e: JsonSyntaxException) {
                snackbarResp.OnRespsnackbar("Что-то пошло не так")


            } finally {
                urlConnection.disconnect()
            }


        }.start()

    }
}