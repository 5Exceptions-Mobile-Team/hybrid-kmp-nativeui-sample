package com.example.kmptestnativeui.weather.domain.repository

interface WeatherRepository {
    suspend fun getWeather(lat: String, long: String) : String
}