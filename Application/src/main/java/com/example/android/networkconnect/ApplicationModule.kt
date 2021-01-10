package com.example.android.networkconnect

import com.example.android.networkconnect.api.RickMortyApi
import com.example.android.networkconnect.repositories.RickMortyRepository
import com.example.android.networkconnect.repositories.RickMortyRepositoryImpl
import com.example.android.networkconnect.viewmodels.RickMortyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {

    //Retrofit singleton instance
    single {
        Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    //RickMortyApi singleton instance
    single {
        get<Retrofit>().create(RickMortyApi::class.java)
    }

    //RickMortyRepository singleton instance
    single<RickMortyRepository> {
        RickMortyRepositoryImpl(get())
    }

    //RickMortyViewModel to bind on RickMortyFragment
    viewModel { RickMortyViewModel(get()) }

}