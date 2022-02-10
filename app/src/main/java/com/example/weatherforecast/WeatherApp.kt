package com.example.weatherforecast

import android.app.Application
import com.example.weatherforecast.di.startDi

class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startDi()
    }
}