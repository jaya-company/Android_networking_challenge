package com.example.android.networkconnect.RickMortyAPI.Service

import android.content.Context
import com.example.android.networkconnect.RickMortyAPI.adapters.CharactersInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class Api(context: Context) {

    val MAIN_ENDPOINT = "https://rickandmortyapi.com/api/"
    private val service: ApiService

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.writeTimeout(60, TimeUnit.SECONDS)


        httpClient.addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json: charset=UTF-8")
                    .build()
            chain.proceed(newRequest)
        }


        val retrofit = Retrofit
                .Builder()
                .baseUrl(MAIN_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

        service = retrofit.create(ApiService::class.java)
    }

    fun getAllCharacters(callback: Callback<CharactersInfo>) {
        val call = service.getAllCharacters()
        call.enqueue(callback)
    }
}

interface ApiService {

    @GET("character")
    fun getAllCharacters(): Call<CharactersInfo>
}
