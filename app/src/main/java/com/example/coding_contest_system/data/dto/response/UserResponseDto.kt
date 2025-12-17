package com.example.coding_contest_system.data.dto.response

import com.example.coding_contest_system.model.Role
import com.example.coding_contest_system.model.User

data class UserResponseDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val groupName: String,
    val groupId: Long,
)

fun UserResponseDto.toDomain(): User {
    val role = if (groupId == "-1".toLong()) Role.TEACHER else Role.STUDENT
    return User(id, firstName, lastName, email, groupName, groupId, role)
}