package com.app.gamesuitver2.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.app.gamesuitver2.data.Repository
import com.app.gamesuitver2.model.UserLogin
import com.app.gamesuitver2.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : ViewModel() {

    var loginResponse  : MutableLiveData<NetworkResult<JsonObject>> = MutableLiveData()

    //fun login() = viewModelScope.launch {
    fun login(email : String, password : String) = viewModelScope.launch {
        repository.login(UserLogin(email, password)).collect { value ->
            loginResponse.value = value
        }
    }


}