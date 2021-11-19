package com.app.gamesuitver2.ui.activity.auth.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.gamesuitver2.databinding.ActivityLoginBinding
import com.app.gamesuitver2.ui.base.BaseActivity

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
      }
}