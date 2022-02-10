package com.example.weatherforecast.di

import com.example.weatherforecast.mappers.CityInfoMapper
import com.example.weatherforecast.mappers.CityInfoMapperImpl
import com.example.weatherforecast.mappers.WeatherInfoMapper
import com.example.weatherforecast.mappers.WeatherInfoMapperImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val mapperModule = module {
    single { CityInfoMapperImpl() } bind CityInfoMapper::class
    single { WeatherInfoMapperImpl() } bind WeatherInfoMapper::class
}