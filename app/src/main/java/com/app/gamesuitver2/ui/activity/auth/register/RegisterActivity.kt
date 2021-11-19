package com.app.gamesuitver2.ui.activity.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.gamesuitver2.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
      }
}