package com.renditriyadi.gamesuitver2.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.renditriyadi.gamesuitver2.R
import com.renditriyadi.gamesuitver2.databinding.ActivityRegisterBinding
import com.renditriyadi.gamesuitver2.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RegisterViewModel::class.java]
        registerViewModel.snackBarText.observe(this, {
            Snackbar.make(
                window.decorView.rootView,
                it,
                Snackbar.LENGTH_LONG
            ).show()
        })

        registerViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        binding.btnCloseRegister.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {

            val username = binding.etUserName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (checkValidation(username, email, password)) {
                registerViewModel.register(username, email, password)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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