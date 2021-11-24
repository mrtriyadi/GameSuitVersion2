package com.app.gamesuitver2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.app.gamesuitver2.data.Repository
import com.app.gamesuitver2.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : ViewModel() {

    var authResponse  : MutableLiveData<NetworkResult<JsonObject>> = MutableLiveData()

    //fun login() = viewModelScope.launch {
    fun auth(token : String) = viewModelScope.launch {
        //repository.login(UserLogin(email, password)).collect { value ->
        repository.auth(token).collect { value ->
            authResponse.value = value
        }
    }

}