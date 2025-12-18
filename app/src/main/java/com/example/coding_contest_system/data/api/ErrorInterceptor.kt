package com.example.coding_contest_system.data.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ErrorInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.isSuccessful) return response

        val rawBody = response.body.string()

        val newBody = rawBody
            .toResponseBody(response.body.contentType())

        return response.newBuilder()
            .body(newBody)
            .build()
    }
}