package com.example.kmptestnativeui.logger

expect object MainLog {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}