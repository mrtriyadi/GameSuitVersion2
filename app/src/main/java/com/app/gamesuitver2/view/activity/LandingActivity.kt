package com.app.gamesuitver2.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.app.gamesuitver2.R
import com.app.gamesuitver2.databinding.ActivityLandingBinding
import com.app.gamesuitver2.databinding.FragmentFormBinding
import com.app.gamesuitver2.view.base.BaseActivity
import com.app.gamesuitver2.view.fragment.FormFragment
import com.app.gamesuitver2.view.fragment.SliderFragment
import com.app.gamesuitver2.utils.ViewPagerAdapter

class LandingActivity : BaseActivity() {
    private lateinit var binding: ActivityLandingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager(){
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(
            SliderFragment(
                "bermain suit dengan teman",
                R.drawable.landing_page1
            ), "Landing Page 1"
        )
        fragmentAdapter.addFragment(
            SliderFragment(
                "bermain suit dengan komputer",
                R.drawable.landing_page2
            ), "Landing Page 2"
        )
        fragmentAdapter.addFragment(
            FormFragment(),"Form Fragment"
        )

        binding.vpIntro.apply {
            adapter = fragmentAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected (position: Int){
                    when{
                        (position ==0) -> {
                            binding.tvNext.visibility = View.VISIBLE
                            binding.tvNext.isEnabled = true
                            binding.tvPrevious.visibility= View.INVISIBLE
                            binding.tvPrevious.isEnabled=false
                        }
                        (position < (fragmentAdapter.itemCount - 1)) ->{
                            binding.tvNext.visibility = View.VISIBLE
                            binding.tvNext.isEnabled = true
                            binding.tvPrevious.visibility= View.VISIBLE
                            binding.tvPrevious.isEnabled=true
                        }
                        (position == (fragmentAdapter.itemCount - 1)) ->{
                            binding.tvNext.visibility = View.INVISIBLE
                            binding.tvNext.isEnabled = false
                            binding.tvPrevious.visibility= View.VISIBLE
                            binding.tvPrevious.isEnabled=true
                        }
                    }
                    super.onPageSelected(position)
                }
            })
        }
        binding.wormDotsIndicator.setViewPager2(binding.vpIntro)
        binding.tvNext.setOnClickListener{
            val bindingForm: FragmentFormBinding = FragmentFormBinding.inflate(layoutInflater)
            if (getNextIndex()!=-1){
                binding.vpIntro.setCurrentItem(getNextIndex(),true)
            }
            if (bindingForm.etName.text.toString() !="" ){
                Toast.makeText(this, "${bindingForm.etName.text}", Toast.LENGTH_LONG).show()
            }

        }

        binding.tvPrevious.setOnClickListener{
            if (getPreviousIndex() != -1){
                binding.vpIntro.setCurrentItem(getPreviousIndex(),true)
            }
        }

    }

    private fun getPreviousIndex(): Int{
        val currentPage = binding.vpIntro.currentItem
        return if (currentPage - 1 >= 0){
            currentPage - 1
        }else{
            -1
        }
    }

    private fun getNextIndex():Int{
        val maxPage = binding.vpIntro.adapter?.itemCount
        val currentIndex = binding.vpIntro.currentItem
        var selectedIndex = -1

        if (currentIndex < maxPage!!){
            selectedIndex = currentIndex +1
        }
        return selectedIndex
    }
}