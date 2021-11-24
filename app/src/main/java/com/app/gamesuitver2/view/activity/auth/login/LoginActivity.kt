package com.app.gamesuitver2.view.activity.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.app.gamesuitver2.databinding.ActivityLoginBinding
import com.app.gamesuitver2.repository.LoginRepository
import com.app.gamesuitver2.service.WebService
import com.app.gamesuitver2.view.activity.auth.login.model.Login
import com.app.gamesuitver2.view.activity.auth.login.viewmodel.LoginViewModel
import com.app.gamesuitver2.view.activity.auth.login.viewmodel.LoginViewModelFactory
import com.app.gamesuitver2.view.activity.auth.register.RegisterActivity
import com.app.gamesuitver2.view.base.BaseActivity

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
      }

    private fun loginUser(){
        val email = binding.etLoginEmail.text.toString()
        val password = binding.etLoginPassword.text.toString()

        val login = Login(email,password)
        val webService = WebService.getInstance()
        val repository = LoginRepository(webService,login)

        loginViewModel = ViewModelProvider(this,LoginViewModelFactory(repository)).get(LoginViewModel::class.java)
        loginViewModel.errorMsg.observe(this,{Toast.makeText(this,it,Toast.LENGTH_SHORT).show()})
        loginViewModel.loading.observe(this,{
            if(it){
                binding.progressBar.visibility = View.VISIBLE
            } else{
                binding.progressBar.visibility = View.GONE
            }
        })

        if(checkValidation(email,password)){
            loginViewModel.login(email,password)
        }
    }

    private fun checkValidation(email: String, password: String): Boolean {
        binding.apply {
            when{
                email.isEmpty() -> {
                    etLoginEmail.error = "Harap diisi"
                    etLoginEmail.requestFocus()
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->{
                    etLoginEmail.error = "Email tidak valid"
                    etLoginEmail.requestFocus()
                }
                password.isEmpty() -> {
                    etLoginPassword.error = "Harap diisi"
                    etLoginPassword.requestFocus()
                }
                else -> return true
            }
        }
        return false
    }

}