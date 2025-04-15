package com.example.kmptestnativeui.logger

import kotlinx.cinterop.toKString
//mport platform.Foundation.NSLog

actual object MainLog {
    actual fun d(tag: String, message: String) {
        //NSLog("DEBUG: [$tag] $message")
    }

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        val errorMsg = throwable?.message ?: "Unknown error"
       // NSLog("ERROR: [$tag] $message\nError: $errorMsg")
    }
}