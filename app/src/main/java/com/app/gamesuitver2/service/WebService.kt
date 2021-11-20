package com.app.gamesuitver2.service

import android.app.Service
import com.app.gamesuitver2.view.activity.auth.login.model.Login
import com.app.gamesuitver2.view.activity.auth.model.AuthKey
import com.app.gamesuitver2.view.activity.auth.model.UserAuth
import com.app.gamesuitver2.view.activity.auth.register.model.Register
import com.app.gamesuitver2.view.activity.auth.model.BattleAuth
import com.app.gamesuitver2.view.activity.game.model.BattleResult
import com.app.gamesuitver2.view.activity.profile.Profile
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface WebService {
    companion object{
        private const val AUTH_REGISTER = "v1/auth/register"
        private const val AUTH_LOGIN = "v1/auth/login"
        private const val AUTH_ME = "v1/auth/me"
        private const val BATTLE = "v1/battle"
        private const val USERS = "v1/users"
        private const val BASE_URL = "https://binar-gdd-cc8.herokuapp.com/"

        private val logInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        private val OkHttpClient = okhttp3.OkHttpClient.Builder()
                .addNetworkInterceptor(logInterceptor)
                .build()

        fun retrofit(): Service {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Service::class.java)
        }
    }
    @POST(AUTH_REGISTER)
    suspend fun registerUser(data: Register): Register
    @POST(AUTH_LOGIN)
    suspend fun loginUser(data: Login): Login
    @GET(AUTH_ME)
    suspend fun getAuthKey(): AuthKey
    @GET(BATTLE)
    suspend fun getBattleResult(): List<BattleResult>
    @GET(BATTLE)
    suspend fun getBattleAuth(): BattleAuth
    @GET(USERS)
    suspend fun getUserAuth(): UserAuth
    @PUT(USERS)
    suspend fun setUserProfile(): List<Profile>



}