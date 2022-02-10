package com.example.weatherforecast.mappers

import com.example.weatherforecast.data.cityresult.CityData
import com.example.weatherforecast.data.cityresult.toCityData
import com.example.weatherforecast.remote.data.CityInfo

interface CityInfoMapper: Function1<List<CityInfo>, List<CityData>>

class CityInfoMapperImpl: CityInfoMapper{
    override fun invoke(p1: List<CityInfo>): List<CityData> {
        val list = mutableListOf<CityData>()
        p1.forEach {
            list.add(it.toCityData())
        }
        return list
    }
}