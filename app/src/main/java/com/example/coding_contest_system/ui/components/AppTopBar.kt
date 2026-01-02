package com.example.coding_contest_system.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.coding_contest_system.state.AuthState
import com.example.coding_contest_system.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    authState: AuthState,
    navController: NavController,
    currentRoute: String
) {
    val showBackArrow = currentRoute != Route.Home.route

    TopAppBar(
        title = {
            TextButton(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = false }
                        launchSingleTop = true
                    }
                }
            ) {
                Text("Coding Contest System")
            }
        },
        navigationIcon = {
            if (showBackArrow) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Назад"
                    )
                }
            }
        },
        actions = {
            if (authState is AuthState.Authorized) {
                TextButton(onClick = { navController.navigate("profile") }) {
                    Text("Профиль")
                }
            } else {
                TextButton(onClick = { navController.navigate("login") }) {
                    Text("Войти")
                }
            }
        }
    )
}