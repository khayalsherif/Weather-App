package com.example.weatherapp.view_model

import android.app.Application
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetLocationViewModel @Inject constructor(
    application: Application,
    private val repository: Repository,
) : BaseViewModel(application)