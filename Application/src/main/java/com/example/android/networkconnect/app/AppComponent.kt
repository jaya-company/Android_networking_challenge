package com.example.android.networkconnect.app

import android.app.Application
import android.content.Context
import com.example.android.networkconnect.MainActivity
import com.example.android.networkconnect.StatusActivity
import com.example.android.networkconnect.datafragment.datainj.ApiModule
import dagger.Component
import javax.inject.Singleton
import dagger.Provides



@Singleton
@Component(modules = [ApiModule::class, AppModule::class])
interface AppComponent {
    fun inject(app: Application)
    fun inject(activity: StatusActivity)
}