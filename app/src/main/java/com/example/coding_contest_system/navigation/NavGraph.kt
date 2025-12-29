package com.example.coding_contest_system.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coding_contest_system.state.AuthState
import com.example.coding_contest_system.ui.screens.auth.LoginScreen
import com.example.coding_contest_system.ui.screens.auth.ProfileScreen
import com.example.coding_contest_system.ui.screens.auth.RegisterScreen
import com.example.coding_contest_system.ui.screens.home.HomeScreen
import com.example.coding_contest_system.viewmodel.AuthViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
) {
    val authState by authViewModel.state.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Authorized) {
            navController.navigate(Route.Home.route) {
                popUpTo(Route.Login.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {

        composable(Route.Home.route) {
            HomeScreen(
                authState = authState,
                onLoginClick = {
                    navController.navigate(Route.Login.route)
                },
                onRegisterClick = {
                    navController.navigate(Route.Register.route)
                }
            )
        }

        composable(Route.Login.route) {
            LoginScreen(
                authViewModel = authViewModel
            )
        }

        composable(Route.Register.route) {
            RegisterScreen(
                onSuccess = {
                    navController.navigate(Route.Login.route) {
                        popUpTo(Route.Register.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Route.Profile.route) {
            RequireAuth(authState, navController) {
                ProfileScreen()
            }
        }
    }
}
