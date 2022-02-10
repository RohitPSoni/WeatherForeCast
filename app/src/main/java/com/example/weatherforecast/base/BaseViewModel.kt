package com.example.weatherforecast.base

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    val errorMessage = MutableLiveData<String?>()
    val progress = ObservableField<Int>()

    protected fun showProgress() {
        progress.set(View.VISIBLE)
    }

    fun hideProgress() {
        progress.set(View.GONE)
    }

    fun showErrorMessage(message: String?){
        errorMessage.postValue(message)
    }
}