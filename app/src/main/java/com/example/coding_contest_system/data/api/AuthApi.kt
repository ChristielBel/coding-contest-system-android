package com.example.coding_contest_system.data.api

import com.example.coding_contest_system.data.dto.request.LoginRequestDto
import com.example.coding_contest_system.data.dto.request.SignupRequestDto
import com.example.coding_contest_system.data.dto.response.UserResponseDto
import com.example.coding_contest_system.data.dto.request.UserUpdateRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthApi {

    @POST("login")
    suspend fun login(@Body payload: LoginRequestDto): Response<Unit>

    @POST("signup")
    suspend fun signup(@Body payload: SignupRequestDto): Response<Unit>

    @GET("me")
    suspend fun me(): Response<UserResponseDto>

    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") userId: Long,
        @Body payload: UserUpdateRequestDto
    ): Response<Unit>

    @POST("logout")
    suspend fun logout(): Response<Unit>

    @POST("refresh")
    suspend fun refresh(): Response<Unit>
}