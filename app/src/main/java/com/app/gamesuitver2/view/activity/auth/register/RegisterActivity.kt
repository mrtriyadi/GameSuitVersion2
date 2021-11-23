package com.app.gamesuitver2.view.activity.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.app.gamesuitver2.R
import com.app.gamesuitver2.databinding.ActivityRegisterBinding
import com.app.gamesuitver2.repository.RegisterRepository
import com.app.gamesuitver2.service.WebService
import com.app.gamesuitver2.view.activity.auth.login.LoginActivity
import com.app.gamesuitver2.view.activity.auth.register.model.Register
import com.app.gamesuitver2.view.activity.auth.register.viewmodel.RegisterViewModel
import com.app.gamesuitver2.view.activity.auth.register.viewmodel.RegisterViewModelFactory
import com.app.gamesuitver2.view.base.BaseActivity

class RegisterActivity : BaseActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.tvHaveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }

    private fun registerUser() {
        val username = binding.etUserName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        val register = Register(username, email, password)
        val webService = WebService.getInstance()
        val repository = RegisterRepository(webService, register)

        registerViewModel = ViewModelProvider(
            this,
            RegisterViewModelFactory(repository)
        ).get(RegisterViewModel::class.java)

        registerViewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        registerViewModel.loading.observe(this, {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        if (checkValidation(username, email, password)) {
            registerViewModel.register(username, email, password)
        }
    }

    private fun checkValidation(username: String, email: String, password: String): Boolean {
        binding.apply {
            when {
                username.isEmpty() -> {
                    etUserName.error = getString(R.string.field_required)
                    etUserName.requestFocus()
                }
                username.length < 6  -> {
                    etUserName.error = getString(R.string.input_invalid)
                    etUserName.requestFocus()
                }
                email.isEmpty() -> {
                    etEmail.error = getString(R.string.field_required)
                    etEmail.requestFocus()
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    etEmail.error = getString(R.string.email_invalid)
                    etEmail.requestFocus()
                }
                password.isEmpty() -> {
                    etPassword.error = getString(R.string.field_required)
                    etPassword.requestFocus()
                }
                else -> return true
            }
        }
        return false
    }
}