package com.example.coding_contest_system.ui.screens.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coding_contest_system.data.result.ApiResult
import com.example.coding_contest_system.data.api.ApiProvider
import com.example.coding_contest_system.data.repository.AuthRepository
import com.example.coding_contest_system.state.LoginState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repo = AuthRepository(ApiProvider.authApi)

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()
    val events = _events.asSharedFlow()

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun login() {
        viewModelScope.launch {
            when (val result = repo.login(state.value.email, state.value.password)) {
                is ApiResult.Success -> {
                    _events.emit(LoginEvent.Success)
                }
                is ApiResult.Error -> {
                    _events.emit(LoginEvent.Error(result.message))
                }
            }
        }
    }
}

sealed interface LoginEvent {
    object Success : LoginEvent
    data class Error(val message: String) : LoginEvent
}