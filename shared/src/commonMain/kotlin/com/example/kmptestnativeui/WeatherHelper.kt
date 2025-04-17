package com.example.kmptestnativeui

import com.example.kmptestnativeui.core.NetworkResult
import com.example.kmptestnativeui.core.remote.createHttpClient
import com.example.kmptestnativeui.weather.data.WeatherRepositoryImpl
import com.example.kmptestnativeui.weather.domain.usecase.WeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class WeatherObservable(val weatherUseCase: WeatherUseCase) {
    private val viewModel = WeatherViewModel(weatherUseCase)
    val weatherState: StateFlow<NetworkResult<String>?> = viewModel.weatherState

    fun getWeather(lat: String, long: String) {
        viewModel.getWeather(lat, long)
    }
}

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase
) {
    private val _weatherState = MutableStateFlow<NetworkResult<String>?>(null)
    val weatherState: StateFlow<NetworkResult<String>?> = _weatherState

    fun getWeather(lat: String, long: String) {
        CoroutineScope(Dispatchers.Default).launch {
            weatherUseCase.getWeather(lat, long).collect {
                _weatherState.value = it
            }
        }
    }

}

// Factory object
object WeatherFactory {
    fun createWeatherObservable(): WeatherObservable {
        val useCase = WeatherUseCase(WeatherRepositoryImpl())
        return WeatherObservable(useCase)
    }
}

class WeatherHelper(private val weatherUseCase: WeatherUseCase) {
    suspend fun getWeather(lat: String, long: String): NetworkResult<String> {
        return weatherUseCase.getWeather(lat, long).first()
    }
}

