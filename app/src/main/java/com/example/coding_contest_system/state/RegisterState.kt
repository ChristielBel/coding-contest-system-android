package com.example.coding_contest_system.state

data class RegisterState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val groupId: Long = 0,
    val error: String? = null
) {
    val isValid: Boolean
        get() =
            firstName.isNotBlank() &&
                    lastName.isNotBlank() &&
                    email.isNotBlank() &&
                    password.length >= 8 &&
                    password == confirmPassword
}