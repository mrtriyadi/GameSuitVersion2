package com.app.gamesuitver2.ui.activity.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.gamesuitver2.databinding.ActivityRegisterBinding
import com.app.gamesuitver2.ui.base.BaseActivity

class RegisterActivity : BaseActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
      }
}