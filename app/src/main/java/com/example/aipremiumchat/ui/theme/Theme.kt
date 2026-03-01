package com.example.aipremiumchat.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

private val DarkColorScheme = darkColorScheme()

@Immutable
data class LuxeThemeTokens(
    val gradients: PremiumGradients
)

object LuxeThemeExtras {
    val gradients = PremiumGradients(
        appBackground = AppGradient,
        actionButton = ActionGradient
    )
}

@Composable
fun LuxeAIChatTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
