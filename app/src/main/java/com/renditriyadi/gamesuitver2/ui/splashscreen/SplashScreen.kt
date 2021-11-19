package com.renditriyadi.gamesuitver2.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.renditriyadi.gamesuitver2.databinding.ActivitySplashScreenBinding
import com.renditriyadi.gamesuitver2.ui.main.MainActivity

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
            Intent(this, MainActivity::class.java)
                .apply {
                    startActivity(this)
                }
            finish()
        }, 3000)
    }
}