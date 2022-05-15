package com.example.weatherapp.view_model

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.data.Repository
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.utils.ErrorParserUtil
import com.example.weatherapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    application: Application,
    private val repository: Repository,
) : BaseViewModel(application) {

    private val _weatherResponse =
        MutableStateFlow<NetworkResult<WeatherResponse>>(NetworkResult.Loading())
    val weatherResponse: StateFlow<NetworkResult<WeatherResponse>> get() = _weatherResponse


    fun getWeatherInformation(lat: Double, long: Double) = viewModelScope.launch {
        _weatherResponse.emit(NetworkResult.Loading())
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getWeather(lat, long)
                if (response.body() == null) {
                    _weatherResponse.emit(NetworkResult.Empty())
                } else {
                    _weatherResponse.emit(handleStartRegistrationResponse(response))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _weatherResponse.emit(NetworkResult.Error("Something went wrong."))
            }
        } else {
            _weatherResponse.emit(NetworkResult.Error("No Internet Connection."))
        }
    }

    private fun handleStartRegistrationResponse(response: Response<WeatherResponse>): NetworkResult<WeatherResponse> {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.isSuccessful -> {
                NetworkResult.Success(response.body())
            }
            else -> {
                NetworkResult.Error(
                    ErrorParserUtil.parseError(response.errorBody()!!)
                )
            }
        }
    }
}