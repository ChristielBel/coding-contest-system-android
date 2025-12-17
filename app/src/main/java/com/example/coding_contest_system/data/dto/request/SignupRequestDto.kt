package com.example.coding_contest_system.data.dto.request

data class SignupRequestDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val groupId: Long,
)
