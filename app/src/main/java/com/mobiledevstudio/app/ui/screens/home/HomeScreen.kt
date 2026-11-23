package com.mobiledevstudio.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobiledevstudio.app.core.ServiceLocator
import com.mobiledevstudio.app.runtime.RuntimeStatus
import com.mobiledevstudio.app.ui.navigation.Screen
import com.mobiledevstudio.app.ui.theme.*

@Composable
fun HomeScreen(
    onNavigate: (Screen) -> Unit,
    serviceLocator: ServiceLocator = ServiceLocator.getInstance()
) {
    val projects by serviceLocator.projectRepository.projects.collectAsState()
    val activeProject by serviceLocator.projectRepository.activeProject.collectAsState()
    val runtimeStatus by serviceLocator.prootManager.status.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(StudioDarkBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Mobile Dev Studio",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = StudioTextPrimary
            )
            Text(
                text = "Autonomous Mobile IDE Workstation",
                style = MaterialTheme.typography.bodyMedium,
                color = StudioTextSecondary
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, StudioBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = StudioDarkSurface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Active Workspace", color = StudioTextSecondary, fontSize = 12.sp)
                    Text(
                        text = activeProject?.name ?: "No project selected",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = StudioTextPrimary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { onNavigate(Screen.Projects) },
                            colors = ButtonDefaults.buttonColors(containerColor = StudioPrimary)
                        ) {
                            Text("Projects", color = StudioDarkBackground)
                        }
                        Button(
                            onClick = { onNavigate(Screen.Terminal) },
                            colors = ButtonDefaults.buttonColors(containerColor = StudioDarkSurfaceVariant)
                        ) {
                            Text("Terminal")
                        }
                    }
                }
            }
        }
    }
}
