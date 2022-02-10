package com.example.weatherforecast.data.weatherresult

import com.example.weatherforecast.common.toCelcius
import com.example.weatherforecast.common.toDDMMYYYHHMMFormat
import com.example.weatherforecast.remote.data.WeatherList

data class WeatherData(
    val date: String?,
    val currTemp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val weather: String,
)


fun WeatherList.toWeatherData(): WeatherData {
    return WeatherData(
        date = this.dt.toDDMMYYYHHMMFormat(),
        currTemp = this.main.temp.toCelcius(),
        tempMax = this.main.temp_max.toCelcius(),
        tempMin = this.main.temp_min.toCelcius(),
        weather = this.weather.first().description
    )
}