package com.example.android.networkconnect.repositories

import com.example.android.networkconnect.api.RickMortyApi
import com.example.android.networkconnect.api.responses.ApiResult
import com.example.android.networkconnect.models.Character
import com.example.android.networkconnect.repositories.base.Repository

/**
 * RickMortyRepositoryImpl implementation
 */
class RickMortyRepositoryImpl(private val rickMortyApi: RickMortyApi) : Repository(), RickMortyRepository {

    /**
     * List of characters
     */
    override suspend fun characters(): ApiResult<List<Character>> {
        return apiCall({ rickMortyApi.listCharacters() }, { it.results })
    }

}