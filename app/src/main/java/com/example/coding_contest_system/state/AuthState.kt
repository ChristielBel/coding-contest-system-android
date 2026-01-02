package com.example.coding_contest_system.state

import com.example.coding_contest_system.model.User

sealed class AuthState {
    object Loading : AuthState()
    object Unauthorized : AuthState()
    data class Authorized(val user: User) : AuthState()
}