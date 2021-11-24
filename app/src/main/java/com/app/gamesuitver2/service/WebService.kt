package com.app.gamesuitver2.service

import android.app.Service
import com.app.gamesuitver2.view.activity.auth.login.model.Login
import com.app.gamesuitver2.view.activity.auth.model.AuthKey
import com.app.gamesuitver2.view.activity.auth.model.UserAuth
import com.app.gamesuitver2.view.activity.auth.register.model.Register
import com.app.gamesuitver2.view.activity.auth.model.BattleAuth
import com.app.gamesuitver2.view.activity.game.model.BattleResult
import com.app.gamesuitver2.view.activity.profile.model.Profile
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface WebService {
    companion object{
        private const val AUTH_REGISTER = "v1/auth/register"
        private const val AUTH_LOGIN = "v1/auth/login"
        private const val AUTH_ME = "v1/auth/me"
        private const val BATTLE = "v1/battle"
        private const val USERS = "v1/users"
        private const val BASE_URL = "https://binar-gdd-cc8.herokuapp.com/api/"

        private val logInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val OkHttpClient = okhttp3.OkHttpClient.Builder()
                .addNetworkInterceptor(logInterceptor)
                .build()

        var retrofitService: WebService? = null
        fun getInstance(): WebService? {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient)
                    .build()
                retrofitService = retrofit.create(WebService::class.java)
            }
            return retrofitService
        }

//        fun retrofit(): Service {
//            return Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(Service::class.java)
//        }
    }

    @Headers("Content-Type:application/json")
    @POST(AUTH_REGISTER)
    suspend fun registerUser(@Body body: Register): Response<ResponseBody>

    @POST(AUTH_LOGIN)
    suspend fun loginUser(@Body data: Login): Response<ResponseBody>

    @GET(AUTH_ME)
    fun getAuthKey(): Response<AuthKey>

    @GET(BATTLE)
    fun getBattleResult(data: BattleResult): Response<BattleResult>

    @GET(BATTLE)
    fun getBattleAuth(): Response<BattleAuth>

    @GET(USERS)
    fun getUserAuth(): Response<UserAuth>

    @PUT(USERS)
    fun setUserProfile(@Body data: Profile): Response<Profile>



}