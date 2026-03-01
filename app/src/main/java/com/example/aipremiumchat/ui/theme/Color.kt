package com.example.aipremiumchat.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class PremiumGradients(
    val appBackground: Brush,
    val actionButton: Brush
)

internal val AppGradient = Brush.verticalGradient(
    listOf(
        Color(0xFF070B18),
        Color(0xFF121C39),
        Color(0xFF1B1240)
    )
)

internal val ActionGradient = Brush.horizontalGradient(
    listOf(Color(0xFF4F8FFF), Color(0xFF7E77FF), Color(0xFFC85DFF))
)
