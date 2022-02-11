package com.example.weatherforecast.ui.citysearch

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.weatherforecast.R
import com.example.weatherforecast.base.BaseFragment
import com.example.weatherforecast.data.cityresult.CityData
import com.example.weatherforecast.databinding.FragmentCitySearchBinding
import com.example.weatherforecast.ui.cityForeCast.CityForeCastFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitySearchFragment : BaseFragment<FragmentCitySearchBinding>() {
    companion object {
        const val CITY_NAME_EXTRA = "CITY NAME EXTRA"
    }

    private val viewModel: CitySearchViewModel by viewModel()
    private val adapter = CitySearchAdapter {data ->
        val bundle = Bundle().apply {
            putString(CityForeCastFragment.CITY_NAME_EXTRA, data.name)
            putDouble(CityForeCastFragment.CITY_NAME_LAT, data.lat)
            putDouble(CityForeCastFragment.CITY_NAME_LOG, data.log)
        }
        findNavController().navigate(R.id.action_citySearchFragment_to_cityForeCastFragment, bundle)
    }

    override fun getViewModel(): ViewModel = viewModel

    override fun getLayoutId(): Int = R.layout.fragment_city_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.invalidCityName.observe(this) {
            showInvalidCityToast()
        }
        viewModel.cityName.observe(this) {
            adapter.updateCities(it)
        }
    }

    override fun bindData() {
        viewBinder.cityName.adapter = adapter
        arguments?.let {
            val name = it.getString(CITY_NAME_EXTRA) ?: ""
            viewModel.searchCity(name)
        } ?: run {
            showInvalidCityToast()
        }
    }

    private fun showInvalidCityToast() {
        Toast.makeText(requireContext(), getString(R.string.invalid_city_name), Toast.LENGTH_LONG)
            .show()
        findNavController().navigateUp()
    }
}