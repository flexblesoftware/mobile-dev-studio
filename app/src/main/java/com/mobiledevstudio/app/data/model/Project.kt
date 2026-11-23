package com.mobiledevstudio.app.data.model

import java.io.File

enum class ProjectTemplate(val displayName: String, val description: String, val category: String) {
    COMPOSE("Jetpack Compose", "Modern declarative UI with Kotlin and Material 3", "Android"),
    KOTLIN_ANDROID("Android Kotlin", "Native Android application with Kotlin View architecture", "Android"),
    JAVA_ANDROID("Android Java", "Traditional Java Android application with XML layouts", "Android"),
    BASIC_ANDROID("Minimal APK", "Ultra-lightweight direct AAPT2 & D8 single-activity app", "Android"),
    WEB_HTML("HTML / CSS / JS", "Frontend web development project with live preview", "Web"),
    REACT("React (Vite)", "Modern React application with TypeScript & Vite", "Web"),
    NODEJS("Node.js Backend", "Express.js REST API server with npm integration", "Backend"),
    PYTHON("Python Script / CLI", "Standalone Python 3 script or CLI application", "Scripting"),
    EMPTY("Empty Workspace", "Clean project directory for custom configuration", "General")
}

data class Project(
    val id: String,
    val name: String,
    val packageName: String,
    val template: ProjectTemplate,
    val rootPath: String,
    val createdAt: Long = System.currentTimeMillis(),
    val lastModified: Long = System.currentTimeMillis()
) {
    val rootDir: File get() = File(rootPath)
}
