package com.example.android.networkconnect.datafragment.datainj

import com.example.android.networkconnect.datafragment.adapter.CharacterListAdapter
import com.example.android.networkconnect.datafragment.model.Character
import com.example.android.networkconnect.datafragment.model.DataApi
import com.example.android.networkconnect.datafragment.model.DataService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    fun provideCharacterApi(): DataApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DataApi::class.java)
    }

    @Provides
    fun provideCharacterService(): DataService {
        return DataService()
    }

    @Provides
    fun provideCharacterList(): ArrayList<Character> {
        return ArrayList()
    }

    @Provides
    fun provideCharacterAdapter(characters: ArrayList<Character>): CharacterListAdapter {
        return CharacterListAdapter(characters)
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}