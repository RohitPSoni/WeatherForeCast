package com.example.weatherforecast.data.weatherresult

sealed class WeatherResult {
    data class WeatherSuccess(val data: List<WeatherData>): WeatherResult()
    data class WeatherException(val exception: Throwable): WeatherResult()
}