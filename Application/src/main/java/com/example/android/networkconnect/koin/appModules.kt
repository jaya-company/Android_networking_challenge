package com.example.android.networkconnect.koin

import com.example.android.networkconnect.network.CharactersRepository
import com.example.android.networkconnect.viewmodel.CharacterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModules = module {

    single { CharactersRepository() }

    viewModel { CharacterViewModel(get()) }
}