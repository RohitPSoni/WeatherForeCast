package com.example.weatherforecast.di

import com.example.weatherforecast.repo.FetchCityRepo
import com.example.weatherforecast.repo.FetchCityRepoImpl
import com.example.weatherforecast.repo.FetchWeatherRepo
import com.example.weatherforecast.repo.FetchWeatherRepoImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val repoModule = module {
    single { FetchCityRepoImpl(get(), get(), get()) } bind FetchCityRepo::class
    single { FetchWeatherRepoImpl(get(), get(), get()) } bind FetchWeatherRepo::class
}