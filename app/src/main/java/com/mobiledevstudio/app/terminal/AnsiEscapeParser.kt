package com.mobiledevstudio.app.terminal

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.mobiledevstudio.app.ui.theme.*

object AnsiEscapeParser {
    private val ANSI_REGEX = "\\u001B\\[([0-9;]*)m".toRegex()

    fun parse(rawText: String): AnnotatedString {
        return buildAnnotatedString {
            var currentIndex = 0
            var currentColor: Color = StudioTextPrimary

            val matches = ANSI_REGEX.findAll(rawText).toList()
            for (match in matches) {
                val textSegment = rawText.substring(currentIndex, match.range.first)
                if (textSegment.isNotEmpty()) {
                    val start = length
                    append(textSegment)
                    addStyle(SpanStyle(color = currentColor), start, length)
                }

                val codes = match.groupValues[1].split(';').mapNotNull { it.toIntOrNull() }
                for (code in codes) {
                    when (code) {
                        0 -> currentColor = StudioTextPrimary
                        31 -> currentColor = StudioError
                        32 -> currentColor = StudioSuccess
                        33 -> currentColor = StudioWarning
                        34 -> currentColor = StudioPrimary
                        35 -> currentColor = StudioAccent
                        else -> currentColor = StudioTextPrimary
                    }
                }
                currentIndex = match.range.last + 1
            }

            if (currentIndex < rawText.length) {
                val start = length
                append(rawText.substring(currentIndex))
                addStyle(SpanStyle(color = currentColor), start, length)
            }
        }
    }
}
