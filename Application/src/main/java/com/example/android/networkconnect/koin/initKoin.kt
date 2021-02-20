package com.example.android.networkconnect.koin

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(
                modules = appModules
        )
    }
}
