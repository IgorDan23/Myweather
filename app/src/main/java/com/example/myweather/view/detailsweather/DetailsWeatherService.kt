package com.example.myweather.view.detailsweather

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.myweather.BuildConfig
import com.example.myweather.repository.WeatherDTO
import com.example.myweather.repository.utils.*
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DetailsWeatherService(val name: String = "") : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {

        intent?.let {
            val lat = it.getDoubleExtra(KEY_LAT,40.1)
            val lon = it.getDoubleExtra(KEY_LON,40.1)


            val urlText = "$DOMAIN${PATH}lat=$lat&lon=$lon"
            val uri = URL(urlText)
            val urlConnection: HttpURLConnection =
                (uri.openConnection() as HttpURLConnection).apply {
                    addRequestProperty("X-Yandex-API-Key",BuildConfig.WEATHER_API_KEY )
                }
            val responseCode = urlConnection.responseCode
            val serverside = 500..599
            val clientside = 400..499
            if (responseCode in serverside) {
              //  snackbarResp.OnRespsnackbar("Oшибка на сервере")
                Thread.sleep(2000)
            } else if (responseCode in clientside) {
              //  snackbarResp.OnRespsnackbar("Что-то пошло не так у клиента")
                Thread.sleep(2000)
            }
            val headers = urlConnection.headerFields
            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))

            try {
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                val message = Intent(WAVE)
                message.putExtra(KEY_Service_Mess, weatherDTO)
                sendBroadcast(message)


            } catch (e: JsonSyntaxException) {
               // snackbarResp.OnRespsnackbar("Что-то пошло не так")


            } finally {
                urlConnection.disconnect()
            }



        }


    }
}