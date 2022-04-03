package com.example.myweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.repository.RepositoryImp

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImp = RepositoryImp()
) : ViewModel() {
    var num: Int = 1

    fun russianWeather() = getWeather(1)
    fun worldWeather() = getWeather(2)
    fun weatherFromServer() = getWeather(3)


    fun getData(): LiveData<AppState> {
        return liveData
    }

    private fun getWeather(numWeatherRep: Int) {
        Thread {
            liveData.postValue(AppState.Loadind)
            when (numWeatherRep) {
                1 -> liveData.postValue(AppState.Success(repository.getRussianWeatherFromLocalStorage()))
                2 -> liveData.postValue(AppState.Success(repository.getWorldWeatherFromLocalStorage()))
                3 -> liveData.postValue(AppState.Success(repository.getWeatherFromServer()))
            }

        }.start()
    }

}