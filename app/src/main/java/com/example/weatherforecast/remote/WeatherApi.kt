package com.example.weatherforecast.remote

import com.example.weatherforecast.remote.data.CityForeCast
import com.example.weatherforecast.remote.data.CityInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("http://api.openweathermap.org/geo/1.0/direct")
    suspend fun getCityName(@Query("q")cityName: String): Response<List<CityInfo>>

    @GET("https://pro.openweathermap.org/data/2.5/forecast/hourly")
    suspend fun getCityForecast(
        @Query("lat") lattitude: Double,
        @Query("lon") longitude: Double) : Response<CityForeCast>
}