package com.example.aipremiumchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.aipremiumchat.ui.ChatScreen
import com.example.aipremiumchat.ui.theme.LuxeAIChatTheme
import com.example.aipremiumchat.ui.theme.LuxeThemeExtras

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LuxeAIChatTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LuxeThemeExtras.gradients.appBackground)
                ) {
                    ChatScreen()
                }
            }
        }
    }
}
