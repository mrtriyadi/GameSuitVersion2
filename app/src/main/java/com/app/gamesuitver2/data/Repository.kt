package com.app.gamesuitver2.data

import android.content.SharedPreferences
import android.graphics.Bitmap
import com.google.gson.JsonObject
import com.app.gamesuitver2.data.remote.RemoteDataSource
import com.app.gamesuitver2.model.BaseApiResponse
import com.app.gamesuitver2.model.UserBattlePost
import com.app.gamesuitver2.model.UserLogin
import com.app.gamesuitver2.model.UserRegister
import com.app.gamesuitver2.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.security.PrivateKey
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun login(userLogin: UserLogin) : Flow<NetworkResult<JsonObject>> {
        return flow<NetworkResult<JsonObject>> {
            emit(safeApiCall { remoteDataSource.login(userLogin) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun register(userRegister: UserRegister) : Flow<NetworkResult<JsonObject>> {
        return flow<NetworkResult<JsonObject>> {
            emit(safeApiCall { remoteDataSource.register(userRegister) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun auth(token: String) : Flow<NetworkResult<JsonObject>> {
        return flow<NetworkResult<JsonObject>> {
            emit(safeApiCall { remoteDataSource.auth("Bearer $token") })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postBattle(token: String, userBattlePost: UserBattlePost) : Flow<NetworkResult<JsonObject>> {
        return flow<NetworkResult<JsonObject>> {
            //emit(safeApiCall { remoteDataSource.auth("Bearer $token") })
            emit(safeApiCall { remoteDataSource.postBattle("Bearer $token", userBattlePost) })
        }.flowOn(Dispatchers.IO)
    }



        fun saveImage(image: Bitmap, storageDir: File, imageFileName: String): Flow<Boolean> {

        val successDirCreated = if (!storageDir.exists()) {
            storageDir.mkdir()
        } else {
            true
        }

        if (successDirCreated) {
            val imageFile = File(storageDir, imageFileName)
            return try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
                flow {
                    emit(true)
                }.flowOn(Dispatchers.IO)
            } catch (e: Exception) {
                e.printStackTrace()
                flow {
                    emit(false)
                }.flowOn(Dispatchers.IO)
            }
        } else {
            return flow { emit(false) }.flowOn(Dispatchers.IO)
        }
    }

}

