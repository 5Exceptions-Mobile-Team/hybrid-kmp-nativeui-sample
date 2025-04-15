package com.example.kmptestnativeui.weather.data

import com.example.kmptestnativeui.core.remote.KtorApi
import com.example.kmptestnativeui.logger.MainLog
import com.example.kmptestnativeui.weather.domain.model.WeatherResponse
import com.example.kmptestnativeui.weather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class WeatherRepositoryImpl(val client : HttpClient) :  WeatherRepository{
    override suspend fun getWeather(lat: String, long: String): String {
        client.config {
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                    //useAlternativeNames = false
                    //isLenient = true
                })
            }
        }

        val response: String = client.get("https://api.open-meteo.com/v1/forecast") {
            parameter("latitude", lat)
            parameter("longitude", long)
            parameter("current", "temperature_2m,wind_speed_10m")
            parameter("hourly", "temperature_2m,relative_humidity_2m,wind_speed_10m")
        }.body()
        MainLog.d("Response","${response}")

        return response
    }
}