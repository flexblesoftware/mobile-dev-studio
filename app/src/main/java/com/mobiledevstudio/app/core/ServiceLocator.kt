package com.mobiledevstudio.app.core

import android.content.Context
import com.mobiledevstudio.app.data.ProjectRepository
import com.mobiledevstudio.app.engine.BuildEngine
import com.mobiledevstudio.app.engine.ApkManager
import com.mobiledevstudio.app.runtime.PRootManager
import com.mobiledevstudio.app.runtime.ToolManager
import com.mobiledevstudio.app.terminal.TerminalManager
import com.mobiledevstudio.app.ai.AiAssistantManager
import com.mobiledevstudio.app.updater.AppUpdateManager

class ServiceLocator private constructor(val context: Context) {

    val projectRepository: ProjectRepository by lazy {
        ProjectRepository(context)
    }

    val terminalManager: TerminalManager by lazy {
        TerminalManager(context)
    }

    val prootManager: PRootManager by lazy {
        PRootManager(context)
    }

    val toolManager: ToolManager by lazy {
        ToolManager(context)
    }

    val buildEngine: BuildEngine by lazy {
        BuildEngine(context, terminalManager)
    }

    val apkManager: ApkManager by lazy {
        ApkManager(context)
    }

    val aiAssistantManager: AiAssistantManager by lazy {
        AiAssistantManager(context)
    }

    val appUpdateManager: AppUpdateManager by lazy {
        AppUpdateManager(context)
    }

    companion object {
        @Volatile
        private var instance: ServiceLocator? = null

        fun init(context: Context): ServiceLocator {
            return instance ?: synchronized(this) {
                instance ?: ServiceLocator(context.applicationContext).also { instance = it }
            }
        }

        fun getInstance(): ServiceLocator {
            return instance ?: throw IllegalStateException("ServiceLocator has not been initialized")
        }
    }
}
