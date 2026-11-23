package com.mobiledevstudio.app.ui.screens.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobiledevstudio.app.core.ServiceLocator
import com.mobiledevstudio.app.ui.theme.*

@Composable
fun EditorScreen(
    serviceLocator: ServiceLocator = ServiceLocator.getInstance()
) {
    var textContent by remember { mutableStateOf(TextFieldValue("// Welcome to Mobile Dev Studio Code Editor\nfun main() {\n    println(\"Ready to code!\")\n}\n")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StudioDarkBackground)
            .padding(12.dp)
    ) {
        Text("Editor", style = MaterialTheme.typography.titleMedium, color = StudioPrimary)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = textContent,
            onValueChange = { textContent = it },
            textStyle = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp,
                color = StudioTextPrimary
            ),
            cursorBrush = SolidColor(StudioPrimary),
            modifier = Modifier.fillMaxSize()
        )
    }
}
