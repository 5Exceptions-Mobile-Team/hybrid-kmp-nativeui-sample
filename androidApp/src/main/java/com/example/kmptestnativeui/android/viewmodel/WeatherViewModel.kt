package com.example.kmptestnativeui.android.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmptestnativeui.core.NetworkResult
import com.example.kmptestnativeui.weather.domain.usecase.WeatherUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.getValue

class WeatherViewModel (
    private val weatherUseCase: WeatherUseCase
): ViewModel() {

    private val _weatherStateFlow =
        MutableStateFlow<NetworkResult<String>?>(NetworkResult.Loading)
    val weatherStateFlow: StateFlow<NetworkResult<String>?> =
        _weatherStateFlow

    fun getWeather(lat: String, long: String) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherUseCase.getWeather(lat,long).collect {
                //val result = Gson().fromJson(it.toString(), WeatherResponse::class.java)
                _weatherStateFlow.value = it
            }
        }
    }
}


