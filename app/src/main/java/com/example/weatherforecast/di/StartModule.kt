package com.example.weatherforecast.di

import com.example.weatherforecast.WeatherApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun WeatherApp.startDi(){
    startKoin {
        androidContext(applicationContext)
        modules(
            networkModule,
            repoModule,
            mapperModule,
            viewModelModule
        )
    }
}