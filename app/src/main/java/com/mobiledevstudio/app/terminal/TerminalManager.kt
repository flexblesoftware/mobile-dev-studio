package com.mobiledevstudio.app.terminal

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import java.util.UUID

class TerminalManager(private val context: Context) {
    private val _sessions = MutableStateFlow<List<TerminalSession>>(emptyList())
    val sessions: StateFlow<List<TerminalSession>> = _sessions

    private val _activeSession = MutableStateFlow<TerminalSession?>(null)
    val activeSession: StateFlow<TerminalSession?> = _activeSession

    init {
        createSession("Main Shell", context.filesDir)
    }

    fun createSession(title: String, workingDir: File): TerminalSession {
        val session = TerminalSession(
            id = UUID.randomUUID().toString(),
            title = title,
            workingDir = workingDir
        )
        val updated = _sessions.value.toMutableList().apply { add(session) }
        _sessions.value = updated
        _activeSession.value = session
        return session
    }

    fun selectSession(session: TerminalSession) {
        _activeSession.value = session
    }

    fun closeSession(session: TerminalSession) {
        session.close()
        val updated = _sessions.value.filter { it.id != session.id }
        _sessions.value = updated
        if (_activeSession.value?.id == session.id) {
            _activeSession.value = updated.firstOrNull()
        }
    }
}
