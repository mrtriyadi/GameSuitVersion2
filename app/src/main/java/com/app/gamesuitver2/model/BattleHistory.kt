package com.app.gamesuitver2.model

import com.google.gson.annotations.SerializedName

data class BattleHistory(
    @SerializedName("_id")
    val _id : String,
    @SerializedName("result")
    val result : String,
    @SerializedName("mode")
    val mode : String,
    @SerializedName("message")
    var message : String,
    @SerializedName("createdAt")
    var createdAt : String,
    @SerializedName("updatedAt")
    var updatedAt : String
)
