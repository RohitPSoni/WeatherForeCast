package com.example.weatherforecast.ui.cityForeCast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.data.weatherresult.WeatherData
import com.example.weatherforecast.databinding.ItemCityWeatherBinding

class CityForecastAdapter : RecyclerView.Adapter<CityForecastAdapter.CityForecastViewHolder>() {
    private val list = mutableListOf<WeatherData>()

    inner class CityForecastViewHolder(private val binder: ItemCityWeatherBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bind(data: WeatherData) {
            with(binder) {
                setVariable(BR.weatherInfo, weather)
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityForecastViewHolder =
        CityForecastViewHolder(
            ItemCityWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CityForecastViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateWeather(list: List<WeatherData>) {
        val start = this.list.size
        this.list.clear()
        notifyItemRangeInserted(start, this.list.size)
    }
}