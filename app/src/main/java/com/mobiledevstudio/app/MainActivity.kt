package com.mobiledevstudio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.mobiledevstudio.app.ui.navigation.Screen
import com.mobiledevstudio.app.ui.screens.ai.AiAssistantScreen
import com.mobiledevstudio.app.ui.screens.apks.ApksScreen
import com.mobiledevstudio.app.ui.screens.editor.EditorScreen
import com.mobiledevstudio.app.ui.screens.home.HomeScreen
import com.mobiledevstudio.app.ui.screens.projects.ProjectsScreen
import com.mobiledevstudio.app.ui.screens.settings.SettingsScreen
import com.mobiledevstudio.app.ui.screens.terminal.TerminalScreen
import com.mobiledevstudio.app.ui.screens.tools.ToolsScreen
import com.mobiledevstudio.app.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileDevStudioTheme {
                MainScreenContent()
            }
        }
    }
}

@Composable
fun MainScreenContent() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = StudioDarkSurface,
                tonalElevation = 8.dp
            ) {
                Screen.bottomNavItems.forEach { screen ->
                    val selected = currentRoute == screen.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = StudioDarkBackground,
                            selectedTextColor = StudioPrimary,
                            indicatorColor = StudioPrimary,
                            unselectedIconColor = StudioTextSecondary,
                            unselectedTextColor = StudioTextSecondary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onNavigate = { screen -> navController.navigate(screen.route) })
            }
            composable(Screen.Projects.route) {
                ProjectsScreen(onNavigate = { screen -> navController.navigate(screen.route) })
            }
            composable(Screen.Editor.route) {
                EditorScreen()
            }
            composable(Screen.Terminal.route) {
                TerminalScreen()
            }
            composable(Screen.AiAssistant.route) {
                AiAssistantScreen()
            }
            composable(Screen.Tools.route) {
                ToolsScreen()
            }
            composable(Screen.Apks.route) {
                ApksScreen()
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
