package com.example.weatherforecast.ui.cityName

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.weatherforecast.R
import com.example.weatherforecast.base.BaseFragment
import com.example.weatherforecast.databinding.FragmentCityNameBinding
import com.example.weatherforecast.ui.citysearch.CitySearchFragment.Companion.CITY_NAME_EXTRA

class CityNameFragment : BaseFragment<FragmentCityNameBinding>() {
    override fun getViewModel(): ViewModel? = null

    override fun getLayoutId(): Int = R.layout.fragment_city_name

    override fun bindData() {
        viewBinder.cityName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                cityName(viewBinder.cityName.text.toString())
            }
            false
        }
        viewBinder.search.setOnClickListener { cityName(viewBinder.cityName.text.toString()) }
    }

    private fun cityName(name: String) {
        val bundle = Bundle().apply {
            putString(CITY_NAME_EXTRA, name)
        }
        findNavController().navigate(R.id.action_cityNameFragment_to_citySearchFragment, bundle)
    }
}