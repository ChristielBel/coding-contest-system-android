package com.example.coding_contest_system.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coding_contest_system.data.api.ApiProvider
import com.example.coding_contest_system.state.AuthState
import com.example.coding_contest_system.data.repository.AuthRepository
import com.example.coding_contest_system.data.result.ApiResult
import com.example.coding_contest_system.util.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repo = AuthRepository(ApiProvider.authApi)

    private val _state = MutableStateFlow<AuthState>(AuthState.Loading)
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    init {
        checkAuth()
    }

    fun checkAuth() {
        viewModelScope.launch {
            when (val result = repo.getUser()) {
                is ApiResult.Success -> {
                    _state.value = AuthState.Authorized(result.data)
                }
                is ApiResult.Error -> {
                    _state.value = AuthState.Unauthorized
                    _events.emit(UiEvent.ShowMessage(result.message))
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            val success = repo.logout()
            _state.value = AuthState.Unauthorized
            val msg = if (success) "Вы вышли из аккаунта" else "Ошибка выхода из аккаунта"
            _events.emit(UiEvent.ShowMessage(msg))
        }
    }
}