package com.app.gamesuitver2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.app.gamesuitver2.databinding.ActivitySplashScreenBinding
import com.bumptech.glide.Glide

class SplashScreen : AppCompatActivity() {
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

        Handler (Looper.getMainLooper()).postDelayed({
            Intent(this, LandingActivity::class.java)
                .apply {
                    startActivity(this)
                }
            finish()
        }, 3000)
    }
}