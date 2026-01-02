package com.example.coding_contest_system.ui.screens.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coding_contest_system.data.api.ApiProvider
import com.example.coding_contest_system.data.error.ApiException
import com.example.coding_contest_system.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repo = AuthRepository(ApiProvider.authApi)

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onFirstNameChange(v: String) = update { copy(firstName = v) }
    fun onLastNameChange(v: String) = update { copy(lastName = v) }
    fun onEmailChange(v: String) = update { copy(email = v) }
    fun onPasswordChange(v: String) = update { copy(password = v) }
    fun onConfirmPasswordChange(v: String) = update { copy(confirmPassword = v) }
    fun onGroupIdChange(v: Long) = update { copy(groupId = v) }

    private fun update(block: RegisterState.() -> RegisterState) {
        _state.update(block)
    }

    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repo.signup(
                    firstName = state.value.firstName,
                    lastName = state.value.lastName,
                    email = state.value.email,
                    password = state.value.password,
                    groupId = state.value.groupId,
                )
                onSuccess()
            } catch (e: ApiException) {
                update { copy(error = e.message) }
            }
        }
    }
}

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
