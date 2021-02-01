package com.example.android.networkconnect.model

import com.google.gson.annotations.Expose

data class Character(
        @Expose
        val image: String,
        @Expose
        val name: String,
)