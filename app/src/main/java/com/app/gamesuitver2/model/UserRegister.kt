package com.app.gamesuitver2.model

import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("email")
    val email : String,
    @SerializedName("username")
    val username : String,
    @SerializedName("password")
    val password : String
)
