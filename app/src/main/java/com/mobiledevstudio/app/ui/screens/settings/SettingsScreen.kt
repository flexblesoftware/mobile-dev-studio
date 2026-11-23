package com.mobiledevstudio.app.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobiledevstudio.app.core.ServiceLocator
import com.mobiledevstudio.app.ui.theme.*

@Composable
fun SettingsScreen(
    serviceLocator: ServiceLocator = ServiceLocator.getInstance()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StudioDarkBackground)
            .padding(16.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium, color = StudioTextPrimary)
        Spacer(modifier = Modifier.height(8.dp))
        Text("App Updates, AI Configuration, and Keystore Management", color = StudioTextSecondary)
    }
}
