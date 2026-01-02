package com.example.coding_contest_system.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coding_contest_system.model.Role
import com.example.coding_contest_system.ui.screens.auth.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    state.user?.let { user ->
        Column(Modifier.padding(24.dp)) {

            Text(
                "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(user.email)
            Text(
                if (user.role == Role.TEACHER) "Преподаватель" else "Студент"
            )

            Spacer(Modifier.height(16.dp))

            Button(onClick = viewModel::toggleEdit) {
                Text(if (state.editing) "Отмена" else "Редактировать")
            }

            if (state.editing) {
                OutlinedTextField(
                    value = state.form.firstName,
                    onValueChange = viewModel::onFirstNameChange,
                    label = { Text("Имя") }
                )

                OutlinedTextField(
                    value = state.form.lastName,
                    onValueChange = viewModel::onLastNameChange,
                    label = { Text("Фамилия") }
                )

                Button(
                    onClick = viewModel::save,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Сохранить")
                }
            }
        }
    }
}
