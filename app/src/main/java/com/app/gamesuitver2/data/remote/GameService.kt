package com.app.gamesuitver2.data.remote

import com.app.gamesuitver2.utils.Constants
import com.google.gson.JsonObject
import com.app.gamesuitver2.model.UserLogin
import com.app.gamesuitver2.model.UserRegister
import com.app.gamesuitver2.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GameService {

    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body userRegister: UserRegister) : Response<JsonObject>

    @Headers("Content-Type: application/json", "Accept:application/json")
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body userLogin: UserLogin) : Response<JsonObject>

}
