package com.example.coding_contest_system.data.result

sealed class ApiResult<out T> {

    data class Success<T>(
        val data: T
    ) : ApiResult<T>()

    data class Error(
        val message: String,
        val code: Int? = null
    ) : ApiResult<Nothing>()
}

inline fun <T, R> ApiResult<T>.map(
    transform: (T) -> R
): ApiResult<R> =
    when (this) {
        is ApiResult.Success -> ApiResult.Success(transform(data))
        is ApiResult.Error -> this
    }