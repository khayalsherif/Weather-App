package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.utils.diifUtils.WeatherDiffUtil

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var emptyList = emptyList<WeatherResponse.Daily>()

    class WeatherViewHolder(val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): WeatherViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWeatherBinding.inflate(layoutInflater, parent, false)
                return WeatherViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.weatherResponse = emptyList[position]
    }

    override fun getItemCount(): Int {
        return emptyList.size
    }

    fun setData(newList: List<WeatherResponse.Daily>) {
        val weatherDiffUtil = WeatherDiffUtil(emptyList, newList)
        val diffUtilResult = DiffUtil.calculateDiff(weatherDiffUtil)
        emptyList = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }

}