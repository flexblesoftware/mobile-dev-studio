package com.mobiledevstudio.app.terminal

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.*

class TerminalSession(
    val id: String,
    val title: String,
    val workingDir: File,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
) {
    private var process: Process? = null
    private var writer: BufferedWriter? = null

    private val _outputLines = MutableStateFlow<List<String>>(listOf("Session started in ${workingDir.absolutePath}"))
    val outputLines: StateFlow<List<String>> = _outputLines

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    init {
        startShell()
    }

    private fun startShell() {
        scope.launch {
            try {
                _isRunning.value = true
                val shellBin = if (File("/system/bin/sh").exists()) "/system/bin/sh" else "sh"
                val pb = ProcessBuilder(shellBin)
                    .directory(workingDir)
                    .redirectErrorStream(true)

                val env = pb.environment()
                env["TERM"] = "xterm-256color"
                env["HOME"] = workingDir.absolutePath

                val proc = pb.start()
                process = proc
                writer = BufferedWriter(OutputStreamWriter(proc.outputStream))

                val reader = BufferedReader(InputStreamReader(proc.inputStream))
                var line: String?

                while (proc.isAlive && reader.readLine().also { line = it } != null) {
                    line?.let { appendLine(it) }
                }

                val exitCode = proc.waitFor()
                appendLine("\n[Process exited with code $exitCode]")
            } catch (e: Exception) {
                appendLine("[Error starting shell: ${e.message}]")
            } finally {
                _isRunning.value = false
            }
        }
    }

    fun sendInput(command: String) {
        scope.launch {
            try {
                writer?.let {
                    it.write(command)
                    if (!command.endsWith("\n")) {
                        it.write("\n")
                    }
                    it.flush()
                }
            } catch (e: Exception) {
                appendLine("[Failed to write: ${e.message}]")
            }
        }
    }

    fun sendInterrupt() {
        process?.destroy()
        appendLine("^C")
        startShell()
    }

    fun clearScreen() {
        _outputLines.value = emptyList()
    }

    private fun appendLine(text: String) {
        val current = _outputLines.value.toMutableList()
        current.add(text)
        if (current.size > 2000) {
            _outputLines.value = current.takeLast(2000)
        } else {
            _outputLines.value = current
        }
    }

    fun close() {
        process?.destroy()
        scope.cancel()
    }
}
