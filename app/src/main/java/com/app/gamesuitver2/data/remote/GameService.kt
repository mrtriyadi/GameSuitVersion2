package com.app.gamesuitver2.data.remote

import com.app.gamesuitver2.model.UserBattlePost
import com.app.gamesuitver2.utils.Constants
import com.google.gson.JsonObject
import com.app.gamesuitver2.model.UserLogin
import com.app.gamesuitver2.model.UserRegister
import com.app.gamesuitver2.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.*

interface GameService {

    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body userRegister: UserRegister) : Response<JsonObject>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body userLogin: UserLogin) : Response<JsonObject>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @GET(Constants.AUTH_URL)
    suspend fun auth(@Header("Authorization") token : String) : Response<JsonObject>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST(Constants.BATTLE_POST_URL)
    suspend fun postBattle(@Header("Authorization") token : String, @Body userBattlePost: UserBattlePost) : Response<JsonObject>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @GET(Constants.BATTLE_POST_URL)
    suspend fun getBattle(@Header("Authorization") token : String) : Response<JsonObject>

}
