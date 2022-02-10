package com.example.weatherforecast.data.cityresult

import com.example.weatherforecast.remote.data.CityInfo

data class CityData(
    val name: String,
    val lat: Double,
    val log: Double,
    val info: String
)

fun CityInfo.toCityData(): CityData {
    val info = this.state?.let { "${this.country}, $it" }?:run{ this.country }
    return CityData(
        name = this.name,
        lat = this.lat,
        log = this.lon,
        info = info
    )
}