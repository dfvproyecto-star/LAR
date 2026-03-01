package com.example.aipremiumchat.ui

import java.time.LocalTime
import java.time.format.DateTimeFormatter

enum class Sender {
    USER,
    AI
}

data class ChatMessage(
    val id: Long,
    val text: String,
    val sender: Sender,
    val time: String = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
)
