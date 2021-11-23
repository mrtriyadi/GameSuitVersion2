package com.app.gamesuitver2.view.activity.auth.register.model

import com.google.gson.annotations.SerializedName


data class Register(
    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String
)
