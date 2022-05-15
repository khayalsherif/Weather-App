package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("daily")
    val daily: List<Daily>,
    @SerializedName("hourly")
    val hourly: List<Hourly>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezone_offset: Int
) {
    data class Current(
        @SerializedName("clouds")
        val clouds: Int,
        @SerializedName("dew_point")
        val dew_point: Double,
        @SerializedName("dt")
        val dt: Int,
        @SerializedName("feels_like")
        val feels_like: Double,
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("sunrise")
        val sunrise: Int,
        @SerializedName("sunset")
        val sunset: Int,
        @SerializedName("temp")
        val temp: Double,
        @SerializedName("uvi")
        val uvi: Double,
        @SerializedName("visibility")
        val visibility: Int,
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("wind_deg")
        val wind_deg: Int,
        @SerializedName("wind_speed")
        val wind_speed: Double
    )

    data class Daily(
        @SerializedName("clouds")
        val clouds: Int,
        @SerializedName("dew_point")
        val dew_point: Double,
        @SerializedName("dt")
        val dt: Int,
        @SerializedName("feels_like")
        val feels_like: FeelsLike,
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("moon_phase")
        val moon_phase: Double,
        @SerializedName("moonrise")
        val moonrise: Int,
        @SerializedName("moonset")
        val moonset: Int,
        @SerializedName("pop")
        val pop: Double,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("rain")
        val rain: Double,
        @SerializedName("sunrise")
        val sunrise: Int,
        @SerializedName("sunset")
        val sunset: Int,
        @SerializedName("temp")
        val temp: Temp,
        @SerializedName("uvi")
        val uvi: Double,
        @SerializedName("weather")
        val weather: List<WeatherX>,
        @SerializedName("wind_deg")
        val wind_deg: Int,
        @SerializedName("wind_gust")
        val wind_gust: Double,
        @SerializedName("wind_speed")
        val wind_speed: Double
    )

    data class Hourly(
        @SerializedName("clouds")
        val clouds: Int,
        @SerializedName("dew_point")
        val dew_point: Double,
        @SerializedName("dt")
        val dt: Int,
        @SerializedName("feels_like")
        val feels_like: Double,
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("pop")
        val pop: Double,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("rain")
        val rain: Rain,
        @SerializedName("temp")
        val temp: Double,
        @SerializedName("uvi")
        val uvi: Double,
        @SerializedName("visibility")
        val visibility: Int,
        @SerializedName("weather")
        val weather: List<WeatherXX>,
        @SerializedName("wind_deg")
        val wind_deg: Int,
        @SerializedName("wind_gust")
        val wind_gust: Double,
        @SerializedName("wind_speed")
        val wind_speed: Double
    )

    data class Weather(
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("main")
        val main: String
    )

    data class FeelsLike(
        @SerializedName("day")
        val day: Double,
        @SerializedName("eve")
        val eve: Double,
        @SerializedName("morn")
        val morn: Double,
        @SerializedName("night")
        val night: Double
    )

    data class Temp(
        @SerializedName("day")
        val day: Double,
        @SerializedName("eve")
        val eve: Double,
        @SerializedName("max")
        val max: Double,
        @SerializedName("min")
        val min: Double,
        @SerializedName("morn")
        val morn: Double,
        @SerializedName("night")
        val night: Double
    ) {
        fun getDayTemperature(): String {
            return (day.toInt() / 10).toString() + "C"
        }
    }

    data class WeatherX(
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("main")
        val main: String
    )

    data class Rain(
        @SerializedName("1h")
        val `1h`: Double
    )

    data class WeatherXX(
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("main")
        val main: String
    )
}