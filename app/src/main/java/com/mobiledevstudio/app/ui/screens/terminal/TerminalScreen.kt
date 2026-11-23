package com.mobiledevstudio.app.ui.screens.terminal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobiledevstudio.app.core.ServiceLocator
import com.mobiledevstudio.app.ui.theme.*

@Composable
fun TerminalScreen(
    serviceLocator: ServiceLocator = ServiceLocator.getInstance()
) {
    val activeSession by serviceLocator.terminalManager.activeSession.collectAsState()
    val outputLines by activeSession?.outputLines?.collectAsState() ?: remember { mutableStateOf(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StudioDarkBackground)
            .padding(12.dp)
    ) {
        Text("Terminal", style = MaterialTheme.typography.titleMedium, color = StudioPrimary)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(outputLines) { line ->
                Text(line, fontFamily = FontFamily.Monospace, fontSize = 12.sp, color = StudioTextPrimary)
            }
        }
    }
}
