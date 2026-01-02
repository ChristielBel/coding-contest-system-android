package com.example.coding_contest_system.state

data class LoginState(
    val email: String = "",
    val password: String = "",
    val error: String? = null
)