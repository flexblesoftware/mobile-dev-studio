package com.mobiledevstudio.app.terminal

import android.app.Service
import android.content.Intent
import android.os.IBinder

class StudioTerminalService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null
}
