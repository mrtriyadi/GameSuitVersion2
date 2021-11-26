package com.app.gamesuitver2.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.*
import com.app.gamesuitver2.R
import com.google.gson.JsonObject
import com.app.gamesuitver2.data.Repository
import com.app.gamesuitver2.model.UserBattlePost
import com.app.gamesuitver2.model.UserLogin
import com.app.gamesuitver2.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : ViewModel() {

    var gameResponse  : MutableLiveData<NetworkResult<JsonObject>> = MutableLiveData()

    fun postBattle(token : String, mode : String, result: String) = viewModelScope.launch {
        repository.postBattle(token, UserBattlePost(mode, result))
    }

}