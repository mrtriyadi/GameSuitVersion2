package com.app.gamesuitver2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gamesuitver2.databinding.FragmentSliderBinding
import com.app.gamesuitver2.ui.base.BaseFragment


class SliderFragment(
    private val desc: String,
    private val imgSrc: Int
) : BaseFragment() {

    private lateinit var binding: FragmentSliderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSliderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataSlider()
    }

    private fun setDataSlider() {
        binding.tvDesc.text=desc
        binding.ivIntro.setImageResource(imgSrc)
    }

}