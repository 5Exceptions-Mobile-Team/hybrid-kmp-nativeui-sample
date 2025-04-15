package com.example.kmptestnativeui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform