package com.example.weatherforecast.data.cityresult
sealed class CityResult {
    data class CityResults(val cities: List<CityData>) : CityResult()
    object CityNameInvalid : CityResult()
    data class CityFetchFailed(val exception: Throwable) : CityResult()
}