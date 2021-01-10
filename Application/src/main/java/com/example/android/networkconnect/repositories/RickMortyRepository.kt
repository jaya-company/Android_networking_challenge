package com.example.android.networkconnect.repositories

import com.example.android.networkconnect.api.responses.ApiResult
import com.example.android.networkconnect.models.Character

/**
 * Repository for Rick and Morty functionality
 */
interface RickMortyRepository {

    suspend fun characters(): ApiResult<List<Character>>

}