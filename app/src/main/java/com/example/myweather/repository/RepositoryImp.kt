package com.example.myweather.repository

class RepositoryImp:Repository {
    override fun getWeatherFromServer(): List<Weather> {
        Thread.sleep(5000)
        return getServerWorldCities()
    }

    override fun getRussianWeatherFromLocalStorage(): List<Weather> {
        Thread.sleep(2000)
        return getRussianCities()
    }

    override fun getWorldWeatherFromLocalStorage(): List<Weather> {
        Thread.sleep(2000)
        return getWorldCities()
    }

}