package com.mobiledevstudio.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Projects : Screen("projects", "Projects", Icons.Default.Folder)
    object Editor : Screen("editor", "Editor", Icons.Default.Edit)
    object Terminal : Screen("terminal", "Terminal", Icons.Default.Terminal)
    object AiAssistant : Screen("ai_assistant", "AI Agent", Icons.Default.Psychology)
    object Tools : Screen("tools", "Tools", Icons.Default.Build)
    object Apks : Screen("apks", "APKs", Icons.Default.Android)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)

    companion object {
        val bottomNavItems = listOf(Home, Projects, Editor, Terminal, AiAssistant)
        val allItems = listOf(Home, Projects, Editor, Terminal, AiAssistant, Tools, Apks, Settings)
    }
}
