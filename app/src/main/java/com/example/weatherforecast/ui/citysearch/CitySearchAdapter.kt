package com.example.weatherforecast.ui.citysearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.data.cityresult.CityData
import com.example.weatherforecast.databinding.ItemCityInfoBinding

class CitySearchAdapter(private val onClick: (CityData) -> Unit) :
    RecyclerView.Adapter<CitySearchAdapter.CitySearchViewHolder>() {
    private val cityList = mutableListOf<CityData>()

    inner class CitySearchViewHolder(private val binder: ItemCityInfoBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bind(data: CityData) {
            with(binder) {
                setVariable(BR.cityInfo, data)
                executePendingBindings()
                root.setOnClickListener {
                    onClick.invoke(data)
                }
            }
        }
    }

    fun updateCities(list: List<CityData>) {
        cityList.clear()
        cityList.addAll(list)
        notifyItemRangeInserted(0, cityList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitySearchViewHolder =
        CitySearchViewHolder(
            ItemCityInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CitySearchViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int = cityList.size
}