package com.example.android.networkconnect.api

import com.example.android.networkconnect.api.responses.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickMortyApi {

    @GET("character")
    suspend fun listCharacters(): Response<CharacterResponse>

}