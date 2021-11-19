package com.app.gamesuitver2.view.activity.auth.register

import android.os.Bundle
import com.app.gamesuitver2.databinding.ActivityRegisterBinding
import com.app.gamesuitver2.view.base.BaseActivity

class RegisterActivity : BaseActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
      }
}