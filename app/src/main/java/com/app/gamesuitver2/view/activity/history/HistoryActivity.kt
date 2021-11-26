package com.app.gamesuitver2.view.activity.history

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.app.gamesuitver2.R
import com.app.gamesuitver2.viewmodel.GameViewModel
import com.app.gamesuitver2.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {

    private val historyViewModel by viewModels<HistoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        historyViewModel.getHistoryData(getSavedToken())
    }

    private fun getSavedToken() : String {
        val sharedPref = getSharedPreferences("GAME_PREFERENCE", Context.MODE_PRIVATE)
        return sharedPref.getString(getString(R.string.token),"EMPTY")!!
    }
}