package com.example.kmptestnativeui.android.di

import com.example.kmptestnativeui.android.viewmodel.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { WeatherViewModel(get()) }
}