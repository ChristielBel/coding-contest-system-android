package com.example.coding_contest_system.ui.screens.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coding_contest_system.data.result.ApiResult
import com.example.coding_contest_system.data.api.ApiProvider
import com.example.coding_contest_system.data.repository.AuthRepository
import com.example.coding_contest_system.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repo = AuthRepository(ApiProvider.authApi)

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null) }

            when (val result = repo.getUser()) {
                is ApiResult.Success -> {
                    val user = result.data
                    _state.update {
                        it.copy(
                            user = user,
                            form = ProfileForm(
                                firstName = user.firstName,
                                lastName = user.lastName
                            ),
                            loading = false
                        )
                    }
                }

                is ApiResult.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    fun toggleEdit() {
        _state.update { it.copy(editing = !it.editing) }
    }

    fun onFirstNameChange(v: String) {
        _state.update { it.copy(form = it.form.copy(firstName = v)) }
    }

    fun onLastNameChange(v: String) {
        _state.update { it.copy(form = it.form.copy(lastName = v)) }
    }

    fun save() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true, error = null) }

            val userId = state.value.user?.id ?: return@launch

            when (
                val result = repo.updateUser(
                    userId = userId,
                    firstName = state.value.form.firstName,
                    lastName = state.value.form.lastName
                )
            ) {
                is ApiResult.Success -> load()
                is ApiResult.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}

data class ProfileState(
    val loading: Boolean = true,
    val editing: Boolean = false,
    val user: User? = null,
    val form: ProfileForm = ProfileForm(),
    val error: String? = null
)

data class ProfileForm(
    val firstName: String = "",
    val lastName: String = ""
)
