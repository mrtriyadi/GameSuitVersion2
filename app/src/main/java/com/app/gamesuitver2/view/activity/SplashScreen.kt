package com.app.gamesuitver2.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import com.app.gamesuitver2.R
import com.app.gamesuitver2.databinding.ActivitySplashScreenBinding
import com.app.gamesuitver2.utils.NetworkResult
import com.app.gamesuitver2.view.activity.game.GameActivity
import com.app.gamesuitver2.view.activity.menu.MenuActivity
import com.app.gamesuitver2.viewmodel.SplashScreenViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    private val splashScreenViewModel by viewModels<SplashScreenViewModel>()
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.let {
            Glide.with(it)
                .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
                .into(binding.ivIntro)
        }

        val token = getSavedToken()
        Log.e("SPLASH", "onCreate: $token", )
        splashScreenViewModel.auth(token)

        Handler (Looper.getMainLooper()).postDelayed({
            splashScreenViewModel.authResponse.observe(this){ response ->
                if (response is NetworkResult.Success)
                    startActivity(Intent(this, MenuActivity::class.java))
                else
                    startActivity(Intent(this, LandingActivity::class.java))
            }
            finish()
        }, 3000)
    }

    private fun getSavedToken() : String {
        val sharedPref = getSharedPreferences("GAME_PREFERENCE", Context.MODE_PRIVATE)
        return sharedPref.getString(getString(R.string.token),"EMPTY")!!
    }

}