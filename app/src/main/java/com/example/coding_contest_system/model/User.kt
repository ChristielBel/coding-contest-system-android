package com.example.coding_contest_system.model

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val groupName: String,
    val groupId: Long,
    val role: Role
)