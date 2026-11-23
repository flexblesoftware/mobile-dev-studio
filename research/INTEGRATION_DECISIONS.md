# Integration Decisions

## Key Architectural Choices:
1. UI Framework: Jetpack Compose + Material 3 for fluid 60fps mobile interfaces.
2. Architecture: MVVM with StateFlow and lightweight Service Locator.
3. Terminal: Dual-mode execution (direct `/system/bin/sh` + PRoot Alpine container).
4. Build Engine: Lightweight direct toolchain (`aapt2`, `d8`, `javac`/`kotlinc`, `apksigner`) with predictable memory under 256MB.
5. Code Editor: Multi-tab editor with tokenizers for 18 languages.
6. AI: Multi-provider client (Gemini, OpenAI, Claude, Groq) with autonomous workspace tools.
