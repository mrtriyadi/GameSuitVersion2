package com.app.gamesuitver2.view.activity.auth.login.model

import com.google.gson.annotations.SerializedName

data class Login(
        @field:SerializedName("email")
        val email: String,
        @field:SerializedName("password")
        val password: String
)