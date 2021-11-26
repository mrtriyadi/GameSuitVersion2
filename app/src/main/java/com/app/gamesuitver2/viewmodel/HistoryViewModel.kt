package com.app.gamesuitver2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.app.gamesuitver2.data.Repository
import com.app.gamesuitver2.model.BattleHistory
import com.app.gamesuitver2.utils.NetworkResult
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : ViewModel() {

    var battleHistory  : MutableLiveData<List<BattleHistory>> = MutableLiveData()

    fun getHistoryData(token : String) = viewModelScope.launch {
        repository.getBattle(token).collect { value ->
            val gson = GsonBuilder().create()
            val results = gson.fromJson(value.data?.get("data")?.asJsonArray,Array<BattleHistory>::class.java).toList()
            battleHistory.value = results
            Log.e("BATTLE HISTORY", "auth: ${results.toString()}", )
        }
    }

}