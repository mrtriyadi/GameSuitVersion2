package com.app.gamesuitver2.view.activity.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.gamesuitver2.repository.LoginRepository

class LoginViewModelFactory(private val repository: LoginRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            LoginViewModel(this.repository) as T
        }else{
            throw IllegalAccessException("Viewmodel Not Found")
        }
    }

}