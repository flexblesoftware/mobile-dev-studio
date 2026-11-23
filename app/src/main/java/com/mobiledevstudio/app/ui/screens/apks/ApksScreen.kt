package com.mobiledevstudio.app.ui.screens.apks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobiledevstudio.app.core.ServiceLocator
import com.mobiledevstudio.app.ui.theme.*

@Composable
fun ApksScreen(
    serviceLocator: ServiceLocator = ServiceLocator.getInstance()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StudioDarkBackground)
            .padding(16.dp)
    ) {
        Text("APK Manager", style = MaterialTheme.typography.headlineMedium, color = StudioTextPrimary)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Manage compiled Android packages and test installations", color = StudioTextSecondary)
    }
}
