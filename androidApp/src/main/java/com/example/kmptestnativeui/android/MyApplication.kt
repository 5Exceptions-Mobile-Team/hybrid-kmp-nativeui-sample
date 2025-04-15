package com.example.kmptestnativeui.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.kmptestnativeui.android.di.appModule
import com.example.kmptestnativeui.di.getSharedModules

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule + getSharedModules())
        }
    }
}