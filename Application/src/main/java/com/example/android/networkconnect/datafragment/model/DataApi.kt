package com.example.android.networkconnect.datafragment.model

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

// Interface to help create GET Req to host
interface DataApi {
    @GET("character")
    fun getCharacters(): Single<DataResponse>
}
