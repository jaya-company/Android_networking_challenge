package com.example.android.networkconnect.app

import android.app.Application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides


@Module
internal class AppModule(private val mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }
}