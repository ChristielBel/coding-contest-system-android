package com.example.coding_contest_system.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val PrimaryLight = Color(0xFF2F80ED)
val SecondaryLight = Color(0xFF95A5A6)
val TertiaryLight = Color(0xFF4CAF50)

val PrimaryDark = Color(0xFF64B5F6)
val SecondaryDark = Color(0xFFB0BEC5)
val TertiaryDark = Color(0xFF81C784)

val ErrorLight = Color(0xFFD32F2F)
val SuccessLight = Color(0xFF388E3C)
val WarningLight = Color(0xFFFFA000)
val InfoLight = Color(0xFF1976D2)

val ErrorDark = Color(0xFFCF6679)
val SuccessDark = Color(0xFF66BB6A)
val WarningDark = Color(0xFFFFB74D)
val InfoDark = Color(0xFF64B5F6)

object AppGradients {
    val lightBackgroundGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFDFE9F3),
            Color(0xFFFFFFFF)
        )
    )

    val darkBackgroundGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF0A1929),
            Color(0xFF1E1E1E)
        )
    )
}