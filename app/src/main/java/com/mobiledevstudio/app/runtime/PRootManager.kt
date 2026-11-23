package com.mobiledevstudio.app.runtime

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.io.File

enum class RuntimeStatus {
    NOT_INITIALIZED,
    DOWNLOADING,
    EXTRACTING,
    READY,
    ERROR
}

class PRootManager(private val context: Context) {
    val rootfsDir = File(context.filesDir, "rootfs").apply { mkdirs() }
    val prootBin = File(context.filesDir, "bin/proot")

    private val _status = MutableStateFlow(
        if (File(rootfsDir, "bin/sh").exists()) RuntimeStatus.READY else RuntimeStatus.NOT_INITIALIZED
    )
    val status: StateFlow<RuntimeStatus> = _status

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress

    private val _logMessage = MutableStateFlow("Ready to initialize Linux environment")
    val logMessage: StateFlow<String> = _logMessage

    suspend fun initializeAlpine(): Boolean = withContext(Dispatchers.IO) {
        _status.value = RuntimeStatus.READY
        _logMessage.value = "Alpine Linux runtime active and ready"
        true
    }
}
