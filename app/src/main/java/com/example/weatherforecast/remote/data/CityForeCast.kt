package com.example.weatherforecast.remote.data

data class CityForeCast(
    val message: String,
    val list: List<WeatherList>
)

data class WeatherList(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val dt_txt: String
)

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val sea_level: Double,
    val grnd_level: Double,
    val humidity: Double,
    val temp_kf: Double
)

data class Weather(
    val main: String,
    val description: String
)

data class Clouds(
    val clouds: Int
)

data class Wind(
    val speed: Double,
    val deg: Double,
    val gust: Double
)