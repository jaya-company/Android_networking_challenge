package com.example.android.networkconnect.app

import android.app.Application

class NetApplication : Application() {

    lateinit var netComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        netComponent = initDagger(this)
    }

    private fun initDagger(app: NetApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}