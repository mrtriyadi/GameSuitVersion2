package com.app.gamesuitver2.model

import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String
)
