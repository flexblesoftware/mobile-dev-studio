package com.mobiledevstudio.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val StudioColorScheme = darkColorScheme(
    primary = StudioPrimary,
    onPrimary = StudioDarkBackground,
    primaryContainer = StudioPrimaryVariant,
    onPrimaryContainer = StudioTextPrimary,
    secondary = StudioSecondary,
    onSecondary = StudioTextPrimary,
    background = StudioDarkBackground,
    onBackground = StudioTextPrimary,
    surface = StudioDarkSurface,
    onSurface = StudioTextPrimary,
    surfaceVariant = StudioDarkSurfaceVariant,
    onSurfaceVariant = StudioTextSecondary,
    outline = StudioBorder,
    error = StudioError,
    onError = StudioTextPrimary
)

@Composable
fun MobileDevStudioTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = StudioColorScheme,
        typography = StudioTypography,
        content = content
    )
}
