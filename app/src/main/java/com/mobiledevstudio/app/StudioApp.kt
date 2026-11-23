package com.mobiledevstudio.app

import android.app.Application
import com.mobiledevstudio.app.core.ServiceLocator

class StudioApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }
}
