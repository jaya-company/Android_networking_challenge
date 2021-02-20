package com.example.android.networkconnect.network

import com.example.android.networkconnect.constants.Connection
import com.example.android.networkconnect.domain.CharacterResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersRepository {
    lateinit var retrofit: Retrofit

    private lateinit var charactersApi: CharactersApi

    companion object {
        var instance: CharactersRepository? = null
            private set
    }

    init {
        instance = this
        initRetrofit()
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
                .baseUrl(Connection.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        charactersApi = retrofit.create(CharactersApi::class.java)
    }

    fun getCharacters(callback: Callback<CharacterResponse>) {
        val call = charactersApi.getCharacters()
        call.enqueue(callback)
    }
}
