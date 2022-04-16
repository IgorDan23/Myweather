package com.example.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myweather.repository.SnackbarResp
import com.example.myweather.view.weatherlist.WeatherListFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
    }




}