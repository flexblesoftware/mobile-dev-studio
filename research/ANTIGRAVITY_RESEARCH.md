# Antigravity CLI Deep Research & Integration Architecture

## 1. Executive Summary
Google Antigravity is an AI-first development suite featuring an interactive IDE and terminal CLI (`agy`). The CLI empowers developers to perform autonomous codebase exploration, file editing, tool execution, and multi-turn workflows directly through an agentic loop.

---

## 2. Antigravity CLI Technical Architecture
- Memory: ARM64 VA39 page alignment support for Android kernels.
- System Calls: Unprivileged Android environments bypass unsupported `faccessat2` calls through glibc/Bionic compatibility shims.
- Sandboxing: Linux bubblewrap namespaces are restricted on Android kernels without root; sandboxing is managed with `"enableTerminalSandbox": false` and `--sandbox=false`.

---

## 3. Configuration & Authentication Schema
- Stored globally at `~/.gemini/antigravity-cli/settings.json`.
- OAuth browser login via Google Account, with fallback one-time verification tokens in headless mode.
- In-App Antigravity management: version inspector, status monitor, and automated update fetcher.
