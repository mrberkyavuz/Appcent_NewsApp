package com.berkyavuz.newsapp

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeLogging()
    }

    private fun initializeLogging() {
        if (isDebuggable(this)) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        fun isDebuggable(context: Context): Boolean {
            return (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        }
    }
}
