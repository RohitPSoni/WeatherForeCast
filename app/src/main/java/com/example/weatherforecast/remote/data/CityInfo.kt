package com.example.weatherforecast.remote.data

data class CityInfo(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?
)
