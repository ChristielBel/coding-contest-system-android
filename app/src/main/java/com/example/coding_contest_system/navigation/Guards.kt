package com.example.coding_contest_system.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.coding_contest_system.state.AuthState
import com.example.coding_contest_system.model.Role

@Composable
fun RequireAuth(
    authState: AuthState,
    navController: NavController,
    content: @Composable () -> Unit
) {
    if (authState is AuthState.Authorized) {
        content()
    } else {
        LaunchedEffect(Unit) {
            navController.navigate("login")
        }
    }
}

@Composable
fun RequireTeacher(
    authState: AuthState,
    navController: NavController,
    content: @Composable () -> Unit
) {
    if (
        authState is AuthState.Authorized &&
        authState.user.role == Role.TEACHER
    ) {
        content()
    } else {
        LaunchedEffect(Unit) {
            navController.navigate("access-denied-role")
        }
    }
}
