package com.app.gamesuitver2.view.activity.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.app.gamesuitver2.databinding.ActivityLoginBinding
import com.app.gamesuitver2.viewmodel.LoginViewModel
import com.app.gamesuitver2.utils.NetworkResult
import com.app.gamesuitver2.view.activity.auth.register.RegisterActivity
import com.app.gamesuitver2.view.activity.game.GameActivity
import com.app.gamesuitver2.view.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loginViewModel.loginResponse.observe(this){response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        Log.e("LOGIN", "Here is the token: ${it["data"].asJsonObject["token"]}", )
                    }
                    startActivity(Intent(this, GameActivity::class.java))

                }

                is NetworkResult.Error -> {
                    var message : String = ""
                    response.data?.let {
                        Log.e("SETTING-error", "onCreate: ${it.toString()}", )
                        message = it["errors"].toString()
                    }
                    if (message.contains('{'))
                        message = response.message.toString()
                    Toast.makeText(
                        this,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.goLoginButton.setOnClickListener {
            loginViewModel.login(binding.etLoginEmail.text.toString(),
                binding.etLoginPassword.text.toString())
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}