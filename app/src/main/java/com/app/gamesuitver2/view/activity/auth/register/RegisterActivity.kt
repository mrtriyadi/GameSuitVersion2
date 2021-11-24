package com.app.gamesuitver2.view.activity.auth.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.app.gamesuitver2.databinding.ActivityRegisterBinding
import com.app.gamesuitver2.utils.NetworkResult
import com.app.gamesuitver2.view.activity.auth.login.LoginActivity
import com.app.gamesuitver2.view.base.BaseActivity
import com.app.gamesuitver2.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private val registerViewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerViewModel.registerResponse.observe(this){response ->
            when (response) {
                is NetworkResult.Success -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }

                is NetworkResult.Error -> {
                    var message : String = ""
                    response.data?.let {
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

        binding.register.setOnClickListener {
            registerViewModel.register(
                binding.etRegisterEmail.text.toString(),
                binding.etRegisterUsername.text.toString(),
                binding.etRegisterPassword.text.toString())
        }

        binding.goLoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}