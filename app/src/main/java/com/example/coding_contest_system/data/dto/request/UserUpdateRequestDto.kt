package com.example.coding_contest_system.data.dto.request

data class UserUpdateRequestDto(
    val firstName: String,
    val lastName: String,
    val email: String? = null,
    val password: String? = null,
    val groupId: Long? = null
)
