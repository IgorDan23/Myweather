package com.example.myweather.repository

class RepositoryImp:Repository {
    override fun getWeatherFromServer(): Weather {
        Thread.sleep(3000)
        return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
        Thread.sleep(3000)
        return Weather()
    }

}