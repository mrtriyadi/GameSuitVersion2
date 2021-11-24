package com.app.gamesuitver2.model

import com.app.gamesuitver2.utils.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import android.widget.Toast
import com.google.gson.JsonObject


abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            //val errMessage : T? = Gson().fromJson<T>(response.errorBody().toString(), JsonObject::class.java)
            val errMessage = Gson().fromJson<T>(response.errorBody()?.charStream()?.readText(), JsonObject::class.java)
            //return NetworkResult.Error("${response.code()} ${response.message()}", errMessage)
            return NetworkResult.Error("${response.code()} ${response.message()}", errMessage)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage", null)

}