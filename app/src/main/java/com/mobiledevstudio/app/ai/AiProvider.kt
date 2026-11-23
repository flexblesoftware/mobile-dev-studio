package com.mobiledevstudio.app.ai

enum class AiProvider(val displayName: String, val defaultEndpoint: String, val defaultModel: String) {
    GEMINI("Google Gemini", "https://generativelanguage.googleapis.com/v1beta", "gemini-1.5-flash"),
    OPENAI("OpenAI", "https://api.openai.com/v1", "gpt-4o"),
    ANTHROPIC("Anthropic Claude", "https://api.anthropic.com/v1", "claude-3-5-sonnet-20240620"),
    GROQ("Groq", "https://api.groq.com/openai/v1", "llama-3.3-70b-versatile"),
    OPENROUTER("OpenRouter", "https://openrouter.ai/api/v1", "auto")
}

data class AiConfiguration(
    val provider: AiProvider = AiProvider.GEMINI,
    val apiKey: String = "",
    val customBaseUrl: String = "",
    val model: String = provider.defaultModel
)
