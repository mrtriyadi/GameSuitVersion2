package com.app.gamesuitver2.view.activity.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.gamesuitver2.R
import com.app.gamesuitver2.databinding.ActivityMenuBinding
import com.app.gamesuitver2.media.InternetVideoActivity
import com.app.gamesuitver2.view.activity.DashboardActivity
import com.app.gamesuitver2.view.activity.game.GameActivity
import com.app.gamesuitver2.view.activity.history.HistoryActivity
import com.app.gamesuitver2.view.activity.profile.ProfileActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupButtonOnAction()
    }

    private fun setupButtonOnAction() {
        binding.suit.setOnClickListener{
            startActivity(Intent(this,DashboardActivity::class.java))
        }
        binding.history.setOnClickListener{
            startActivity(Intent(this,HistoryActivity::class.java))
        }
        binding.profile.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.btnVidTutorial.setOnClickListener{
            startActivity(Intent(this, InternetVideoActivity::class.java))
        }
    }
}