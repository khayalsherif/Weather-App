package com.example.weatherapp.data.network

import com.example.weatherapp.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {
    @GET(EndPoints.WEATHER)
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") key: String
    ): Response<WeatherResponse>
}