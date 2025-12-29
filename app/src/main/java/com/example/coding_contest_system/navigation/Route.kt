package com.example.coding_contest_system.navigation

sealed class Route(val route: String) {
    object Home : Route("home")
    object Login : Route("login")
    object Register : Route("register")
    object Profile : Route("profile")
}