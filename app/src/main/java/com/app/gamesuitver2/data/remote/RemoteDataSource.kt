package com.app.gamesuitver2.data.remote

import com.app.gamesuitver2.model.UserLogin
import com.app.gamesuitver2.model.UserRegister
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val gameService: GameService) {

//    suspend fun getDog() =
//        gameService.getDog()

    suspend fun login(userLogin: UserLogin) = gameService.login(userLogin)

    suspend fun register(userRegister: UserRegister) = gameService.register(userRegister)

}