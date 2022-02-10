package com.example.weatherforecast.repo

import com.example.weatherforecast.data.weatherresult.WeatherResult
import com.example.weatherforecast.mappers.WeatherInfoMapper
import com.example.weatherforecast.remote.ApiMapper
import com.example.weatherforecast.remote.WeatherApi

interface FetchWeatherRepo {
    suspend fun fetchWeather(lat: Double, log: Double): WeatherResult
}

class FetchWeatherRepoImpl(
    private val api: WeatherApi,
    private val mapper: WeatherInfoMapper,
    private val apiMapper: ApiMapper
) : FetchWeatherRepo {
    override suspend fun fetchWeather(lat: Double, log: Double): WeatherResult {
        val result = apiMapper.apiCall { api.getCityForecast(lattitude = lat, longitude = log) }
        return result.fold(
            onSuccess = {
            WeatherResult.WeatherSuccess(mapper.invoke(it))
        }, onFailure = {
            WeatherResult.WeatherException(it)
        })
    }
}