package com.mobiledevstudio.app.runtime

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class DeveloperTool(
    val name: String,
    val description: String,
    val binaryName: String,
    val installCommand: String,
    val isInstalled: Boolean = false,
    val version: String = "Not detected"
)

class ToolManager(private val context: Context) {
    private val _tools = MutableStateFlow<List<DeveloperTool>>(
        listOf(
            DeveloperTool("Java (OpenJDK 17)", "Java runtime for Android builds", "java", "apk add openjdk17", true, "17.0.10"),
            DeveloperTool("Git", "Version control system", "git", "apk add git", true, "2.43.0"),
            DeveloperTool("Node.js", "JavaScript runtime", "node", "apk add nodejs npm", true, "v20.11.1"),
            DeveloperTool("Python 3", "Python interpreter", "python3", "apk add python3", true, "3.11.8"),
            DeveloperTool("AAPT2", "Android packaging tool", "aapt2", "apk add android-tools", true, "34.0.0")
        )
    )
    val tools: StateFlow<List<DeveloperTool>> = _tools
}
