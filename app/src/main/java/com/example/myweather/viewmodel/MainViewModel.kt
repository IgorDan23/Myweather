package com.example.myweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.repository.RepositoryImp

open class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImp = RepositoryImp()
) : ViewModel() {
    var num: Int = 1


    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getWeather() {
        Thread {

            liveData.postValue(AppState.Loadind)
            if (num == 1) {
                liveData.postValue(AppState.Success(repository.getWeatherFromServer()))
            } else liveData.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))

        }.start()
    }

}