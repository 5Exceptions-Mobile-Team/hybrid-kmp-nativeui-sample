package com.example.kmptestnativeui.core.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual fun createHttpClient(): HttpClient {
   return HttpClient(OkHttp)
}