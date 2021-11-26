package com.app.gamesuitver2.model

import com.google.gson.annotations.SerializedName

data class UserBattlePost(
    @SerializedName("mode")
    val mode : String,
    @SerializedName("result")
    val result : String
)
