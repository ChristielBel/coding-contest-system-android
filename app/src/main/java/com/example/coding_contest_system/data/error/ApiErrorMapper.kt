package com.example.coding_contest_system.data.error

import com.example.coding_contest_system.data.result.ApiResult
import com.google.gson.Gson

object ApiErrorMapper {

    fun map(code: Int, serverMessage: String?): String =
        when (code) {
            400 -> serverMessage ?: "Ошибка валидации"
            401 -> "Сессия истекла. Войдите заново."
            403 -> "Ошибка авторизации"
            404 -> "Ресурс не найден"
            409 -> "Пользователь уже существует"
            500 -> "Ошибка сервера"
            else -> serverMessage ?: "Неизвестная ошибка ($code)"
        }
}

suspend fun <T> safeApiCall(
    call: suspend () -> retrofit2.Response<T>
): ApiResult<T> {
    return try {
        val response = call()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ApiResult.Success(body)
            } else {
                ApiResult.Error("Пустой ответ от сервера")
            }
        } else {
            val errorBody = response.errorBody()?.string()

            val serverMessage = runCatching {
                Gson().fromJson(errorBody, ApiError::class.java).message
            }.getOrNull()

            ApiResult.Error(
                message = ApiErrorMapper.map(response.code(), serverMessage),
                code = response.code()
            )
        }

    } catch (e: Exception) {
        ApiResult.Error("Проверьте подключение к интернету")
    }
}
