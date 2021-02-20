package com.example.android.networkconnect

import android.app.Application
import com.example.android.networkconnect.koin.initKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}