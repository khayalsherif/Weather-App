package com.example.weatherapp.data

import com.example.weatherapp.data.network.NetworkApi
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.utils.Constants.Companion.BASE_KEY
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {
    //Weather
    suspend fun getWeather(
        lat: Double, long: Double
    ): Response<WeatherResponse> {
        return networkApi.getWeather(lat, long, BASE_KEY)
    }
}