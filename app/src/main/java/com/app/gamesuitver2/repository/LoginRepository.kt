package com.app.gamesuitver2.repository

import com.app.gamesuitver2.service.WebService
import com.app.gamesuitver2.view.activity.auth.login.model.Login

class LoginRepository(private val webService: WebService?,private val loginData: Login) {
    suspend fun login() = webService?.loginUser(loginData)
}