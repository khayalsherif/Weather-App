package com.example.weatherapp.utils.diifUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.models.WeatherResponse

class WeatherDiffUtil(
    private val oldList: List<WeatherResponse.Daily>,
    private val newList: List<WeatherResponse.Daily>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}