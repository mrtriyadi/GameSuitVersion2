package com.renditriyadi.gamesuitver2.model

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("username")
    val username : String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String
)
