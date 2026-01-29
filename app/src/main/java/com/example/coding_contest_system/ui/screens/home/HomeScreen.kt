package com.example.coding_contest_system.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coding_contest_system.R
import com.example.coding_contest_system.state.AuthState
import com.example.coding_contest_system.model.Role
import com.example.coding_contest_system.model.User
import com.example.coding_contest_system.ui.components.FeatureCard

@Composable
fun HomeScreen(
    authState: AuthState,
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    when (authState) {
        AuthState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        AuthState.Unauthorized -> {
            GuestHome(
                onLoginClick = onLoginClick,
                onRegisterClick = onRegisterClick
            )
        }

        is AuthState.Authorized -> {
            when (authState.user.role) {
                Role.STUDENT -> StudentHome(authState.user)
                Role.TEACHER -> TeacherHome(authState.user)
            }
        }
    }
}

@Composable
fun GuestHome(
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.welcome_guest),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.login))
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.registration))
        }
    }
}

@Composable
fun StudentHome(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome_student, user.firstName, user.lastName),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        FeatureCard(
            title = stringResource(R.string.contest_title),
            description = stringResource(R.string.contest_student_description)
        )

        FeatureCard(
            title = stringResource(R.string.results_title),
            description = stringResource(R.string.results_student_description)
        )
    }
}

@Composable
fun TeacherHome(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome_teacher, user.firstName, user.lastName),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        FeatureCard(
            title = stringResource(R.string.contest_title),
            description = stringResource(R.string.contest_teacher_description),
        )
        FeatureCard(
            title = stringResource(R.string.tasks_title),
            description = stringResource(R.string.tasks_description)
        )
        FeatureCard(
            title = stringResource(R.string.students_title),
            description = stringResource(R.string.students_description)
        )
        FeatureCard(
            title = stringResource(R.string.groups_title),
            description = stringResource(R.string.groups_description)
        )
        FeatureCard(
            title = stringResource(R.string.results_title),
            description = stringResource(R.string.results_teacher_description)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenGuestPreview() {
    HomeScreen(
        authState = AuthState.Unauthorized
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenLoadingPreview() {
    HomeScreen(
        authState = AuthState.Loading
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenStudentPreview() {
    HomeScreen(
        authState = AuthState.Authorized(
            user = User(
                id = 1,
                firstName = "Ivan",
                lastName = "Petrov",
                email = "ivan@student.com",
                groupName = "14",
                groupId = 1,
                role = Role.STUDENT
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenTeacherPreview() {
    HomeScreen(
        authState = AuthState.Authorized(
            user = User(
                id = 2,
                firstName = "Anna",
                lastName = "Ivanova",
                email = "anna@teacher.com",
                groupName = "teacher",
                groupId = -1,
                role = Role.TEACHER
            )
        )
    )
}
