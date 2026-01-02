package com.example.coding_contest_system.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coding_contest_system.ui.screens.auth.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        OutlinedTextField(state.firstName, viewModel::onFirstNameChange, label = { Text("Имя") })
        OutlinedTextField(state.lastName, viewModel::onLastNameChange, label = { Text("Фамилия") })
        OutlinedTextField(state.email, viewModel::onEmailChange, label = { Text("Email") })
        OutlinedTextField(state.password, viewModel::onPasswordChange, label = { Text("Пароль") })
        OutlinedTextField(
            state.confirmPassword,
            viewModel::onConfirmPasswordChange,
            label = { Text("Повторите пароль") }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.register(onSuccess) },
            enabled = state.isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зарегистрироваться")
        }

        state.error?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = Color.Red)
        }
    }
}
