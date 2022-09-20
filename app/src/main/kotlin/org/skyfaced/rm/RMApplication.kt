package org.skyfaced.rm

import android.app.Application
import logcat.AndroidLogcatLogger

class RMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidLogcatLogger.installOnDebuggableApp(this)
    }
}