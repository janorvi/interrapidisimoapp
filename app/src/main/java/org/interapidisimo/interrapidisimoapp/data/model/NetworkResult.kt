package org.interapidisimo.interrapidisimoapp.data.model

sealed class NetworkResult<T: Any> {

    class ApiSuccess<T: Any>(
        val code: Int,
        val data: T
    ): NetworkResult<T>()

    class ApiError<T: Any>(
        val code: Int,
        val message: String
    ): NetworkResult<T>()

    class ApiException<T: Any>(val e: Throwable): NetworkResult<T>()
}
