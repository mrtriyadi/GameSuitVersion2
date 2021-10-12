package com.renditriyadi.gamesuitver2.ui.menu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.renditriyadi.gamesuitver2.databinding.ActivityMenuBinding
import com.renditriyadi.gamesuitver2.ui.game.Game

class Menu : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var playerName:String

    companion object{
        const val NAME=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
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
        val intent= Intent(this, Game::class.java)

        binding.ivWithFriend.setOnClickListener {
            intent.putExtra(Game.NAME,"$playerName")
            intent.putExtra(Game.ENEMY,"Player 2")
            startActivity(intent)
        }

        binding.ivWithComp.setOnClickListener {
            intent.putExtra(Game.NAME,"$playerName")
            intent.putExtra(Game.ENEMY,"Computer")
            startActivity(intent)
        }
    }

    private fun getIntentData() {
        playerName = intent.getStringExtra(NAME).toString()
    }
}