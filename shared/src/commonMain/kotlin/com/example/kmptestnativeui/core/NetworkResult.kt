package com.example.kmptestnativeui.core


//sealed class NetworkResult<out T> {
//    data class Success<out T>(val data: T) : NetworkResult<T>()
//    data class Error(val message: String) : NetworkResult<Nothing>()
//    object Loading : NetworkResult<Nothing>()
//}

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val message: String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}