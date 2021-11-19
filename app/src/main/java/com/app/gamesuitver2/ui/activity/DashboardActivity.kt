package com.app.gamesuitver2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.gamesuitver2.databinding.ActivityDashboardBinding
import com.app.gamesuitver2.ui.activity.game.GameActivity
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var playerName:String

    companion object{
        const val NAME=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
    }

    override fun onStart() {
        super.onStart()
        showSnackbar()
        goToGame()

    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(binding.root,"Hello $playerName",Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Close"){
            snackbar.dismiss()
        }
        snackbar.show()
    }

    private fun goToGame() {
        val intent= Intent(this, GameActivity::class.java)

        binding.ivWithFriend.setOnClickListener {
            intent.putExtra(GameActivity.NAME, playerName)
            intent.putExtra(GameActivity.ENEMY,"Player 2")
            startActivity(intent)
        }

        binding.ivWithComp.setOnClickListener {
            intent.putExtra(GameActivity.NAME, playerName)
            intent.putExtra(GameActivity.ENEMY,"Computer")
            startActivity(intent)
        }
    }

    private fun getIntentData() {
        playerName = intent.getStringExtra(NAME).toString()
    }
}