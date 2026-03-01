package com.example.aipremiumchat.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aipremiumchat.ui.theme.LuxeThemeExtras

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {
    val listState = rememberLazyListState()

    LaunchedEffect(viewModel.messages.size, viewModel.isTyping) {
        val extra = if (viewModel.isTyping) 1 else 0
        val target = (viewModel.messages.size + extra - 1).coerceAtLeast(0)
        listState.animateScrollToItem(target)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .padding(horizontal = 16.dp, vertical = 18.dp)
    ) {
        PremiumHeader()
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(viewModel.messages, key = { it.id }) { message ->
                MessageBubble(message = message)
            }
            item {
                AnimatedVisibility(
                    visible = viewModel.isTyping,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    TypingBubble()
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        InputBar(
            input = viewModel.currentInput,
            onInputChange = viewModel::onInputChange,
            onSend = viewModel::sendMessage
        )
    }
}

@Composable
private fun PremiumHeader() {
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFF5B9DFF), Color(0xFF9F7CFF), Color(0xFFE05BFF))
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xAA121A33))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(gradient),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.AutoAwesome,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Nova Prime IA",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Asistente profesional · Premium",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFD0D8FF)
                )
            }
        }
    }
}

@Composable
private fun MessageBubble(message: ChatMessage) {
    val isUser = message.sender == Sender.USER
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (isUser) 18.dp else 4.dp,
                bottomEnd = if (isUser) 4.dp else 18.dp
            ),
            color = if (isUser) Color(0xFF5D78FF) else Color(0xCC182342),
            tonalElevation = 8.dp,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth(0.82f)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = message.time,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFCFD7FF)
                )
            }
        }
    }
}

@Composable
private fun TypingBubble() {
    val infinite = rememberInfiniteTransition(label = "typing")
    val alpha1 by infinite.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
        label = "a1"
    )
    val alpha2 by infinite.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(900), RepeatMode.Reverse),
        label = "a2"
    )
    val alpha3 by infinite.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1100), RepeatMode.Reverse),
        label = "a3"
    )

    Row(modifier = Modifier.fillMaxWidth()) {
        Surface(
            color = Color(0xB31A284A),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.padding(end = 44.dp)
        ) {
            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Dot(alpha = alpha1)
                Spacer(modifier = Modifier.width(6.dp))
                Dot(alpha = alpha2)
                Spacer(modifier = Modifier.width(6.dp))
                Dot(alpha = alpha3)
            }
        }
    }
}

@Composable
private fun Dot(alpha: Float) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .alpha(alpha)
            .clip(CircleShape)
            .background(Color(0xFFE9EDFF))
    )
}

@Composable
private fun InputBar(
    input: String,
    onInputChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = input,
            onValueChange = onInputChange,
            placeholder = { Text("Escribe tu consulta estratégica...") },
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = onSend,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(LuxeThemeExtras.gradients.actionButton)
        ) {
            Icon(
                imageVector = Icons.Rounded.Send,
                contentDescription = "Enviar",
                tint = Color.White
            )
        }
    }
}
