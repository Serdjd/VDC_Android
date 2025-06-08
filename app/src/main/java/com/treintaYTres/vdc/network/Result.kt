package com.treintaYTres.vdc.network

sealed class Result<T> (
    open val data: T? = null,
    val message: String? = null
) {
    class Success<T>(override val data: T) : Result<T>(data)
    class Loading<T>() : Result<T>()
    class Error<T>(message: String) : Result<T>(message = message)
}