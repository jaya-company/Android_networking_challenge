package com.example.android.networkconnect.datafragment.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("name")
    val name: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("image")
    val image: String?
)