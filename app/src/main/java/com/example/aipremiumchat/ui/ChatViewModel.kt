package com.example.aipremiumchat.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    var messages by mutableStateOf(
        listOf(
            ChatMessage(
                id = 1,
                text = "Hola, soy Nova Prime. Tu asistente de IA profesional. ¿En qué proyecto estratégico te apoyo hoy?",
                sender = Sender.AI
            )
        )
    )
        private set

    var currentInput by mutableStateOf("")
        private set

    var isTyping by mutableStateOf(false)
        private set

    fun onInputChange(value: String) {
        currentInput = value
    }

    fun sendMessage() {
        val trimmed = currentInput.trim()
        if (trimmed.isEmpty()) return

        val userMessage = ChatMessage(
            id = System.currentTimeMillis(),
            text = trimmed,
            sender = Sender.USER
        )
        messages = messages + userMessage
        currentInput = ""

        viewModelScope.launch {
            isTyping = true
            delay(1200)
            val aiReply = ChatMessage(
                id = System.currentTimeMillis() + 1,
                text = buildPremiumReply(trimmed),
                sender = Sender.AI
            )
            messages = messages + aiReply
            isTyping = false
        }
    }

    private fun buildPremiumReply(prompt: String): String {
        return "Excelente solicitud: \"$prompt\". Te propongo una estrategia en 3 pasos: 1) Definir objetivo medible, 2) Diseñar plan de ejecución con hitos, 3) Revisar KPIs semanalmente para escalar resultados."
    }
}
