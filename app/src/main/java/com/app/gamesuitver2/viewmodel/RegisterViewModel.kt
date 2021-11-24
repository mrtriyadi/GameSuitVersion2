package com.app.gamesuitver2.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.app.gamesuitver2.data.Repository
import com.app.gamesuitver2.model.UserLogin
import com.app.gamesuitver2.model.UserRegister
import com.app.gamesuitver2.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : ViewModel() {

    var registerResponse  : MutableLiveData<NetworkResult<JsonObject>> = MutableLiveData()

    fun register(email : String, username: String, password : String) = viewModelScope.launch {
        repository.register(UserRegister(email, username, password)).collect { value ->
            registerResponse.value = value
        }
    }

}