package com.example.android.networkconnect.network

import com.example.android.networkconnect.constants.Connection
import com.example.android.networkconnect.domain.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET

interface CharactersApi {
    @GET(Connection.URL_GET_CHARACTERS) // making get request at marvel end-point
    fun getCharacters(): Call<CharacterResponse>
}