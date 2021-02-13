package com.example.android.networkconnect.RickMortyAPI.adapters

import java.io.Serializable

data class CharactersInfo(val results: List<Character>): Serializable

data class Character(val name: String,
                     val image: String,
                     val gender: String): Serializable