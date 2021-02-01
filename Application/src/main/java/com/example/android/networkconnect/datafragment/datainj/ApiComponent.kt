package com.example.android.networkconnect.datafragment.datainj

import com.example.android.networkconnect.MainActivity
import com.example.android.networkconnect.StatusActivity
import com.example.android.networkconnect.app.AppModule
import com.example.android.networkconnect.datafragment.model.DataService
import com.example.android.networkconnect.datafragment.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class, AppModule::class])
interface ApiComponent {

    fun inject(service: DataService)

    fun inject(viewModel: ListViewModel)

    fun inject(activity: MainActivity)

    fun inject(activity: StatusActivity)


}