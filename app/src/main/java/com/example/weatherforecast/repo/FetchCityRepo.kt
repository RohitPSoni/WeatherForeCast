package com.example.weatherforecast.repo

import com.example.weatherforecast.common.isValidCity
import com.example.weatherforecast.data.cityresult.CityResult
import com.example.weatherforecast.mappers.CityInfoMapper
import com.example.weatherforecast.remote.ApiMapper
import com.example.weatherforecast.remote.WeatherApi

interface FetchCityRepo {
    suspend fun getCity(name: String): CityResult
}

class FetchCityRepoImpl(
    private val api: WeatherApi,
    private val mapper: CityInfoMapper,
    private val apiMapper: ApiMapper
) : FetchCityRepo {
    override suspend fun getCity(name: String): CityResult {
        return if (name.isValidCity().not()) {
            CityResult.CityNameInvalid
        } else {
            val result = apiMapper.apiCall { api.getCityName(name) }
            result.fold(onSuccess = {
                if (it.isEmpty()) {
                    CityResult.CityNameInvalid
                } else {
                    val data = mapper.invoke(it)
                    CityResult.CityResults(data)
                }
            },
                onFailure = {
                    CityResult.CityFetchFailed(it)
                })
        }
    }
}