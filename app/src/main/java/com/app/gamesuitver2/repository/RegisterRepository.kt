package com.app.gamesuitver2.repository

import com.app.gamesuitver2.service.WebService
import com.app.gamesuitver2.view.activity.auth.register.model.Register

class RegisterRepository(private val webService: WebService?, private val registerData: Register) {

    suspend fun register() = webService?.registerUser(registerData)

}