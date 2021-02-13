package com.example.android.networkconnect.RickMortyAPI.Service

import android.content.Context
import com.example.android.networkconnect.RickMortyAPI.adapters.CharactersInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterService(val context: Context, val api: Api = Api(context)) {

    fun fetchData(onComplete: (response: CharactersInfo?, error: String?) -> Unit) {

        api.getAllCharacters(object : Callback<CharactersInfo> {

            override fun onFailure(call: Call<CharactersInfo>, t: Throwable) {
                onComplete(null, t.localizedMessage)
            }

            override fun onResponse(
                    call: Call<CharactersInfo>,
                    response: Response<CharactersInfo>
            ) {
                val requestResult = response.body()

                if (requestResult == null) {
                    onComplete(null, response.message())

                    return
                }

                if (requestResult == null) {
                    onComplete(null, response.message())

                    return
                }

                onComplete(requestResult, null)
            }
        })
    }
}