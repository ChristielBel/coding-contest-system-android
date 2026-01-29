package com.example.coding_contest_system.ui.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    tertiary = TertiaryLight,

    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),

    error = ErrorLight,
    errorContainer = Color(0xFFF9DEDC),
    onError = Color.White,
    onErrorContainer = Color(0xFF410E0B),

    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFC4C7C5),
    scrim = Color(0xFF000000),
    surfaceBright = Color(0xFFF9F9F9),
    surfaceContainer = Color(0xFFF3F3F3),
    surfaceContainerHigh = Color(0xFFEDEDED),
    surfaceContainerHighest = Color(0xFFE8E8E8),
    surfaceContainerLow = Color(0xFFF9F9F9),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceDim = Color(0xFFD9D9D9),
    surfaceTint = PrimaryLight,
    surfaceVariant = Color(0xFFE7E0EC)
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,

    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = Color(0xFFE6E1E5),
    onSurface = Color(0xFFE6E1E5),

    error = ErrorDark,
    errorContainer = Color(0xFF8C1D18),
    onError = Color.Black,
    onErrorContainer = Color(0xFFF9DEDC),

    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF444746),
    scrim = Color(0xFF000000),
    surfaceBright = Color(0xFF3B3B3B),
    surfaceContainer = Color(0xFF252525),
    surfaceContainerHigh = Color(0xFF2F2F2F),
    surfaceContainerHighest = Color(0xFF3A3A3A),
    surfaceContainerLow = Color(0xFF202020),
    surfaceContainerLowest = Color(0xFF0F0F0F),
    surfaceDim = Color(0xFF121212),
    surfaceTint = PrimaryDark,
    surfaceVariant = Color(0xFF49454F)
)

object AppColors {
    @Composable
    fun success(): Color {
        val isDarkTheme = isSystemInDarkTheme()
        return if (isDarkTheme) SuccessDark else SuccessLight
    }

    @Composable
    fun warning(): Color {
        val isDarkTheme = isSystemInDarkTheme()
        return if (isDarkTheme) WarningDark else WarningLight
    }

    @Composable
    fun info(): Color {
        val isDarkTheme = isSystemInDarkTheme()
        return if (isDarkTheme) InfoDark else InfoLight
    }

    @Composable
    fun error(): Color {
        return MaterialTheme.colorScheme.error
    }
}

@Composable
fun CodingContestSystemTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography
    ) {
        val gradient = if (darkTheme) {
            AppGradients.darkBackgroundGradient
        } else {
            AppGradients.lightBackgroundGradient
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            content()
        }
    }
}