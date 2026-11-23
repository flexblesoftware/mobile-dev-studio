package com.mobiledevstudio.app.ui.screens.projects

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
import com.mobiledevstudio.app.data.model.Project
import com.mobiledevstudio.app.ui.navigation.Screen
import com.mobiledevstudio.app.ui.theme.*

@Composable
fun ProjectsScreen(
    onNavigate: (Screen) -> Unit,
    serviceLocator: ServiceLocator = ServiceLocator.getInstance()
) {
    val projects by serviceLocator.projectRepository.projects.collectAsState()
    val activeProject by serviceLocator.projectRepository.activeProject.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StudioDarkBackground)
            .padding(16.dp)
    ) {
        Text("Projects", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(projects) { project ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, if (project.id == activeProject?.id) StudioPrimary else StudioBorder, RoundedCornerShape(10.dp))
                        .clickable {
                            serviceLocator.projectRepository.setActiveProject(project)
                            onNavigate(Screen.Editor)
                        },
                    colors = CardDefaults.cardColors(containerColor = StudioDarkSurface)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(project.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = StudioTextPrimary)
                        Text(project.packageName, color = StudioTextSecondary, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
