package com.example.android.networkconnect.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.networkconnect.domain.Character
import com.example.android.networkconnect.domain.CharacterResponse
import com.example.android.networkconnect.network.CharactersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel(private val repository: CharactersRepository): ViewModel() {
    var listCharacters: MutableLiveData<List<Character>> = MutableLiveData()
    var isFetching = MutableLiveData<Boolean>(false)
    private var lastCharacters: List<Character> = listOf()

    private fun startFetching() {
        isFetching.postValue(true)
    }

    private fun processFinished() {
        isFetching.postValue(false)
    }

    fun getCharacters() {
        startFetching()
        repository.getCharacters(object: Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                val requestResult = response.body()
                listCharacters.postValue(requestResult?.results)
                lastCharacters = requestResult?.results!!
                processFinished()
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("Error", "Error on request")
            }
        })
    }

    fun getLastCharacters(): List<Character> {
        return lastCharacters
    }

    fun clearCharacters() {
        lastCharacters = listOf()
        listCharacters.postValue(listOf())
    }
}
