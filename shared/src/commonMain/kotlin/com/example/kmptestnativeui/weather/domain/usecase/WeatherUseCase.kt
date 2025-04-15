package com.example.kmptestnativeui.weather.domain.usecase

import com.example.kmptestnativeui.core.NetworkResult
import com.example.kmptestnativeui.weather.domain.repository.WeatherRepository
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.util.logging.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.invoke

class WeatherUseCase(val weatherRepository: WeatherRepository) {

    fun getWeather(lat: String, long: String) : Flow<NetworkResult<String>> =
        flow {
            emit(NetworkResult.Loading)

            val response = weatherRepository.getWeather(lat, long)
            //emit(NetworkResult.Success(response))
            emit(NetworkResult.Success(response))
        }.catch {
            val error = when (it) {
                is SocketTimeoutException, is ConnectTimeoutException -> {
                    NetworkResult.Error("Server Not Reachable")
                }
                else -> {
                    NetworkResult.Error("Some error occured ${it.message}")
                }
            }
            it.printStackTrace()
            emit(error)
        }.flowOn(Dispatchers.IO)
}


