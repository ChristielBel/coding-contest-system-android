package com.example.coding_contest_system.data.repository

import com.example.coding_contest_system.data.result.ApiResult
import com.example.coding_contest_system.data.error.safeApiCall
import com.example.coding_contest_system.data.result.map
import com.example.coding_contest_system.data.api.AuthApi
import com.example.coding_contest_system.data.dto.request.LoginRequestDto
import com.example.coding_contest_system.data.dto.request.SignupRequestDto
import com.example.coding_contest_system.data.dto.request.UserUpdateRequestDto
import com.example.coding_contest_system.data.dto.response.toDomain
import com.example.coding_contest_system.model.User

class AuthRepository(private val api: AuthApi) {

    suspend fun login(
        email: String,
        password: String
    ): ApiResult<Unit> =
        safeApiCall {
            api.login(LoginRequestDto(email, password))
        }

    suspend fun signup(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        groupId: Long,
    ) {
        api.signup(
            SignupRequestDto(
                firstName,
                lastName,
                email,
                password,
                groupId,
            )
        )
    }

    suspend fun getUser(): ApiResult<User> =
        safeApiCall {
            api.me()
        }.map { it.toDomain() }

    suspend fun updateUser(
        userId: Long,
        firstName: String,
        lastName: String,
        email: String? = null,
        password: String? = null,
        groupId: Long? = null
    ): ApiResult<Unit> =
        safeApiCall {
            api.updateUser(
                userId,
                UserUpdateRequestDto(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    groupId = groupId
                )
            )
        }

    suspend fun logout(): Boolean =
        runCatching { api.logout() }.isSuccess

    suspend fun refresh(): Boolean =
        runCatching { api.refresh() }.isSuccess
}