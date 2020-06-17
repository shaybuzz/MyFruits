package com.tut.mywebsocket.view

import android.app.Application
import timber.log.Timber

class FruitsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}