package com.example.coding_contest_system.data.api

import com.example.coding_contest_system.config.ApiConfig
import com.example.coding_contest_system.data.api.SessionCookieJar
import com.example.coding_contest_system.data.api.ErrorInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private lateinit var retrofitAuth: Retrofit
    lateinit var authApi: AuthApi
        private set

    fun init(config: ApiConfig) {
        val okHttp = OkHttpClient.Builder()
            .cookieJar(SessionCookieJar())
            .addInterceptor(ErrorInterceptor())
            .build()

        retrofitAuth = Retrofit.Builder()
            .baseUrl(config.authUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authApi = retrofitAuth.create(AuthApi::class.java)
    }
}