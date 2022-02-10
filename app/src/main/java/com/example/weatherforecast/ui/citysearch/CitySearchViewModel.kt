package com.example.weatherforecast.ui.citysearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.base.BaseViewModel
import com.example.weatherforecast.data.cityresult.CityData
import com.example.weatherforecast.data.cityresult.CityResult
import com.example.weatherforecast.repo.FetchCityRepo
import kotlinx.coroutines.launch

class CitySearchViewModel(private val repo: FetchCityRepo) : BaseViewModel() {
    val cityName = MutableLiveData<List<CityData>>()
    val invalidCityName = MutableLiveData<Unit>()

    fun searchCity(name: String) {
        showProgress()
        viewModelScope.launch {
            when (val result = repo.getCity(name)) {
                is CityResult.CityResults -> cityName.postValue(result.cities)
                is CityResult.CityFetchFailed -> showErrorMessage(result.exception.localizedMessage)
                is CityResult.CityNameInvalid -> invalidCityName.postValue(Unit)
            }
            hideProgress()
        }
    }
}