package com.example.weatherforecast.ui.cityForeCast

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.base.BaseViewModel
import com.example.weatherforecast.data.weatherresult.WeatherData
import com.example.weatherforecast.data.weatherresult.WeatherResult
import com.example.weatherforecast.repo.FetchWeatherRepo
import kotlinx.coroutines.launch

class CityForeCastViewModel(
    private val repo: FetchWeatherRepo
) : BaseViewModel() {
    val weatherList = MutableLiveData<List<WeatherData>>()
    val name = ObservableField<String>()

    fun fetchWeatherData(cityName: String, lat: Double, log: Double) {
        name.set(cityName)
        showProgress()
        viewModelScope.launch {
            val result = repo.fetchWeather(lat, log)
            when (result) {
                is WeatherResult.WeatherSuccess -> weatherList.postValue(result.data)
                is WeatherResult.WeatherException -> showErrorMessage(result.exception.localizedMessage)
            }
            hideProgress()
        }
    }
}