package com.app.gamesuitver2.data.remote

import com.app.gamesuitver2.model.UserBattlePost
import com.app.gamesuitver2.model.UserLogin
import com.app.gamesuitver2.model.UserRegister
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val gameService: GameService) {

    suspend fun login(userLogin: UserLogin) = gameService.login(userLogin)

    suspend fun register(userRegister: UserRegister) = gameService.register(userRegister)

    suspend fun auth(token : String) = gameService.auth(token)

    suspend fun postBattle(token : String, userBattlePost: UserBattlePost) = gameService.postBattle(token, userBattlePost)

    suspend fun getBattle(token : String) = gameService.getBattle(token)

}