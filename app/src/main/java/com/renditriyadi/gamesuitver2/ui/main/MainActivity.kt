package com.renditriyadi.gamesuitver2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.renditriyadi.gamesuitver2.R
import com.renditriyadi.gamesuitver2.databinding.ActivityMainBinding
import com.renditriyadi.gamesuitver2.databinding.FragmentFormBinding
import com.renditriyadi.gamesuitver2.ui.formfragment.FormFragment
import com.renditriyadi.gamesuitver2.ui.sliderfragment.SliderFragment
import com.renditriyadi.gamesuitver2.utils.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
            lateinit var bindingForm: FragmentFormBinding
            bindingForm = FragmentFormBinding.inflate(layoutInflater)
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