package com.example.kmptestnativeui.di

import com.example.kmptestnativeui.core.remote.createHttpClient
import com.example.kmptestnativeui.weather.data.WeatherRepositoryImpl
import com.example.kmptestnativeui.weather.domain.repository.WeatherRepository
import com.example.kmptestnativeui.weather.domain.usecase.WeatherUseCase
import org.koin.dsl.bind
import org.koin.dsl.module

class SharedModule {
}

val viewModelModule = module {

//    viewModel {
//        ViewLoggedIntervalsViewModel()
//    }
}

fun getSharedModules() = listOf(
    accountModule
)


private val accountModule = module {
    factory { WeatherRepositoryImpl() }
    factory { WeatherUseCase(weatherRepository = get()) }
    single<WeatherRepository> { WeatherRepositoryImpl() }
}

val useCaseModule = module {
    factory { WeatherUseCase(weatherRepository = get()) }
}

val repositoryModule = module {
    factory { WeatherRepositoryImpl() } bind WeatherRepository::class
}