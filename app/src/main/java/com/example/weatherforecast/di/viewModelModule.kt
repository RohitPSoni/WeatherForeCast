package com.example.weatherforecast.di

import com.example.weatherforecast.ui.cityForeCast.CityForeCastViewModel
import com.example.weatherforecast.ui.citysearch.CitySearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CitySearchViewModel(get()) }
    viewModel { CityForeCastViewModel(get()) }
}