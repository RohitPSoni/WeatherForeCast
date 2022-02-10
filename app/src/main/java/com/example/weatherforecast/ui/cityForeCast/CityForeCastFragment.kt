package com.example.weatherforecast.ui.cityForeCast

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.weatherforecast.R
import com.example.weatherforecast.base.BaseFragment
import com.example.weatherforecast.databinding.FragmentCityForecastBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityForeCastFragment: BaseFragment<FragmentCityForecastBinding>() {
    companion object {
        const val CITY_NAME_EXTRA = "City Name Extra"
        const val CITY_NAME_LAT = "City Name Lat"
        const val CITY_NAME_LOG = "City Name Log"
    }
    private val viewModel: CityForeCastViewModel by viewModel()
    private val adapter = CityForecastAdapter()

    override fun getViewModel(): ViewModel = viewModel

    override fun getLayoutId(): Int = R.layout.fragment_city_forecast

    override fun bindData() {
        with(viewBinder) {
            weatherList.adapter = adapter
            actionBar.setNavigationIcon(R.drawable.arrow_back)
            actionBar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        arguments?.let {
            val name = it.getString(CITY_NAME_EXTRA)?: ""
            val lat = it.getDouble(CITY_NAME_LAT)
            val log = it.getDouble(CITY_NAME_LOG)

            viewModel.fetchWeatherData(name, lat, log)
        }

        viewModel.weatherList.observe(this) {
            adapter.updateWeather(it)
        }
    }
}