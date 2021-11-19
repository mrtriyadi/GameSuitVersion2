package com.app.gamesuitver2.view.activity.auth.login

import android.os.Bundle
import com.app.gamesuitver2.databinding.ActivityLoginBinding
import com.app.gamesuitver2.view.base.BaseActivity

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
      }
}