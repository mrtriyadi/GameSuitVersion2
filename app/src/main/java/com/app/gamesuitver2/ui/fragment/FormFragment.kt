package com.app.gamesuitver2.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gamesuitver2.databinding.FragmentFormBinding
import com.app.gamesuitver2.ui.activity.Menu

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        putName()
    }

    private fun putName() {
        binding.btnSetName.setOnClickListener {
            navigateToMenu()
        }
    }

    private fun navigateToMenu() {
        val intent = Intent (this.context, Menu::class.java)
        intent.putExtra(Menu.NAME,"${binding.etName.text}")
        startActivity(intent)
    }
    /*
    private fun putName() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lateinit var bindingMain: ActivityMainBinding
                bindingMain = ActivityMainBinding.inflate(layoutInflater)
                if (binding.etName.text.toString() == ""){
                    bindingMain.tvNext.visibility = View.INVISIBLE
                    bindingMain.tvNext.isEnabled = false
                } else{
                    bindingMain.tvNext.visibility = View.VISIBLE
                    bindingMain.tvNext.isEnabled = true
                }
            }

        })
    }
    */

}