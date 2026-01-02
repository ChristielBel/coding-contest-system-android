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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            text = "Добро пожаловать 👋",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "В системе контроля работ вы можете создавать и решать задания, получать мгновенную обратную связь и отслеживать прогресс.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Войти")
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Регистрация")
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
            text = "Привет, ${user.firstName} ${user.lastName} 🎓",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        FeatureCard(
            title = "Контрольные",
            description = "Просматривай доступные контрольные и задания"
        )

        FeatureCard(
            title = "Результаты",
            description = "Решай контрольные и получай мгновенную обратную связь"
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
            text = "Здравствуйте, ${user.firstName} ${user.lastName} 👨‍🏫",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        FeatureCard("Контрольные", "Создание и управление контрольными")
        FeatureCard("Задания", "Редактирование и обновление заданий")
        FeatureCard("Студенты", "Просмотр и управление студентами")
        FeatureCard("Группы", "Управление учебными группами")
        FeatureCard("Результаты", "Анализ успеваемости студентов")
    }
}

