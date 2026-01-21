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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coding_contest_system.R
import com.example.coding_contest_system.state.RegisterState
import com.example.coding_contest_system.ui.screens.auth.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    RegisterScreenContent(
        state = state,
        onFirstNameChange = viewModel::onFirstNameChange,
        onLastNameChange = viewModel::onLastNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onRegisterClick = { viewModel.register(onSuccess) }
    )
}

@Composable
private fun RegisterScreenContent(
    state: RegisterState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(Modifier.padding(24.dp)) {

        OutlinedTextField(
            state.firstName,
            onFirstNameChange,
            label = { Text(stringResource(R.string.name)) }
        )

        OutlinedTextField(
            state.lastName,
            onLastNameChange,
            label = { Text(stringResource(R.string.surname)) }
        )

        OutlinedTextField(
            state.email,
            onEmailChange,
            label = { Text(stringResource(R.string.email)) }
        )

        OutlinedTextField(
            state.password,
            onPasswordChange,
            label = { Text(stringResource(R.string.password)) }
        )

        OutlinedTextField(
            state.confirmPassword,
            onConfirmPasswordChange,
            label = { Text(stringResource(R.string.repeat_password)) }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onRegisterClick,
            enabled = state.isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.register))
        }

        state.error?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = Color.Red)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreenContent(
        state = RegisterState(
            firstName = "Ivan",
            lastName = "Petrov",
            email = "ivan@test.com",
            password = "123456",
            confirmPassword = "123456",
            error = null
        ),
        onFirstNameChange = {},
        onLastNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onRegisterClick = {}
    )
}
