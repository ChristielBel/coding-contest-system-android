package com.example.coding_contest_system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coding_contest_system.config.ApiConfigProvider
import com.example.coding_contest_system.data.api.ApiProvider
import com.example.coding_contest_system.state.AuthState
import com.example.coding_contest_system.viewmodel.AuthViewModel
import com.example.coding_contest_system.ui.components.AppTopBar
import com.example.coding_contest_system.navigation.AppNavGraph
import com.example.coding_contest_system.navigation.Route
import com.example.coding_contest_system.ui.screens.SplashScreen
import com.example.coding_contest_system.util.UiEvent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiConfigProvider.init(this)
        ApiProvider.init(ApiConfigProvider.config)

        setContent {
            MaterialTheme {
                AppRoot()
            }
        }
    }
}

@Composable
fun AppRoot() {

    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        authViewModel.events.collect { event ->
            when (event) {
                is UiEvent.ShowMessage ->
                    snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Route.Home.route


    Scaffold(
        topBar = { AppTopBar(authState, navController, currentRoute) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when (authState) {
                AuthState.Loading -> SplashScreen()

                else -> AppNavGraph(
                    navController = navController,
                    authViewModel = authViewModel,
                )
            }
        }
    }
}
