package com.example.weatherforecast.mappers

import com.example.weatherforecast.data.weatherresult.WeatherData
import com.example.weatherforecast.data.weatherresult.toWeatherData
import com.example.weatherforecast.remote.data.CityForeCast

interface WeatherInfoMapper : Function1<CityForeCast, List<WeatherData>>

class WeatherInfoMapperImpl: WeatherInfoMapper {
    override fun invoke(p1: CityForeCast): List<WeatherData> {
        val list = mutableListOf<WeatherData>()
        p1.list.forEach {
            list.add(it.toWeatherData())
        }
        return list
    }
}

