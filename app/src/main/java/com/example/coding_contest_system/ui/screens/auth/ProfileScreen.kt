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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coding_contest_system.R
import com.example.coding_contest_system.model.Role
import com.example.coding_contest_system.model.User
import com.example.coding_contest_system.ui.screens.auth.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    state.user?.let { user ->
        ProfileScreenContent(
            user = user,
            editing = state.editing,
            onEditClick = viewModel::toggleEdit,
            onFirstNameChange = viewModel::onFirstNameChange,
            onLastNameChange = viewModel::onLastNameChange,
            onSaveClick = viewModel::save
        )
    }
}

@Composable
private fun ProfileScreenContent(
    user: User,
    editing: Boolean,
    onEditClick: () -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    Column(Modifier.padding(24.dp)) {

        Text(
            "${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(user.email)
        Text(
            if (user.role == Role.TEACHER)
                stringResource(R.string.teacher)
            else
                stringResource(R.string.student)
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = onEditClick) {
            Text(
                if (editing)
                    stringResource(R.string.cancel)
                else
                    stringResource(R.string.edit)
            )
        }

        if (editing) {
            OutlinedTextField(
                value = user.firstName,
                onValueChange = onFirstNameChange,
                label = { Text(stringResource(R.string.name)) }
            )

            OutlinedTextField(
                value = user.lastName,
                onValueChange = onLastNameChange,
                label = { Text(stringResource(R.string.surname)) }
            )

            Button(
                onClick = onSaveClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.save))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(
        user = User(
            id = 1,
            firstName = "Ivan",
            lastName = "Petrov",
            email = "ivan@test.com",
            groupName = "14",
            groupId = 1,
            role = Role.STUDENT,
        ),
        editing = true,
        onEditClick = {},
        onFirstNameChange = {},
        onLastNameChange = {},
        onSaveClick = {}
    )
}
