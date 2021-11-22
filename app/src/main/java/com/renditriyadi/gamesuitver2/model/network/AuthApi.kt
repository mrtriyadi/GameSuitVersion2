package com.renditriyadi.gamesuitver2.model.network

import com.renditriyadi.gamesuitver2.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

interface AuthApi {

    @Headers("Content-Type:application/json")
    @POST("auth/register")
    fun register(
        @Body body: User
    ): Call<ResponseBody>
}