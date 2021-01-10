package com.example.android.networkconnect.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.networkconnect.api.responses.ApiResult
import com.example.android.networkconnect.models.Character
import com.example.android.networkconnect.repositories.RickMortyRepository
import kotlinx.coroutines.launch

/**
 * MainViewModel designed to retrieve the results from Rick and Morty
 */
class RickMortyViewModel(private val rickMortyRepository: RickMortyRepository) : ViewModel() {

    private val _characters: MutableLiveData<List<Character>> by lazy { MutableLiveData<List<Character>>() }

    /**
     * LiveData that emits a List of characters responses from query
     */
    val characters: LiveData<List<Character>> get() = _characters


    private val _charactersError: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    /**
     * LiveData that emits errors from characters functionality
     */
    val charactersError: LiveData<String> get() = _charactersError

    private val _charactersException: MutableLiveData<Throwable> by lazy { MutableLiveData<Throwable>() }

    /**
     * LiveData that emits exceptions from characters functionality
     */
    val charactersException: LiveData<Throwable> get() = _charactersException


    private var _charactersLoading = false

    /**
     * True while search is processing, false otherwise
     */
    val charactersLoading: Boolean get() = _charactersLoading


    /**
     * Search items from the API based on a query and an offset
     */
    fun characters() {
        if (!_charactersLoading) {
            _charactersLoading = true
            viewModelScope.launch {
                when (val charactersResult = rickMortyRepository.characters()) {
                    is ApiResult.Success -> {
                        _characters.postValue(charactersResult.data)
                    }
                    is ApiResult.Error -> {
                        _charactersError.postValue(charactersResult.error)
                    }
                    is ApiResult.Exception -> {
                        _charactersException.postValue(charactersResult.exception)
                    }
                }
                _charactersLoading = false
            }
        }
    }
}