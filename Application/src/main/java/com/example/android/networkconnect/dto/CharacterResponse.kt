package com.example.android.networkconnect.dto

import com.example.android.networkconnect.model.Character
import com.google.gson.annotations.Expose

class CharacterResponse(@Expose val results: List<Character> = listOf())