package com.example.android.networkconnect.domain
import java.io.Serializable

data class Character(val name: String, val image: String) : Serializable

data class CharacterResponse(val results: List<Character>) : Serializable