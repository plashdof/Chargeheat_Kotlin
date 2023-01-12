package com.week2.chargepig.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.week2.chargepig.R
import com.week2.chargepig.databinding.FragmentHomeBinding
import com.week2.chargepig.view.adapter.HomeVPAdapter

class HomeFragment : Fragment(){
    private lateinit var binding : FragmentHomeBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.btnFind.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_findFragment)
        }

        binding.btnEchopoint.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_echopointFragment)
        }
        val datas = arrayListOf<Int>(R.drawable.test, R.drawable.test2, R.drawable.test3)

        val adapter = HomeVPAdapter(datas)
        binding.viewpager.adapter = adapter

        // indicator 생성, viewpager 과 연결

        val indicator = binding.indicator
        indicator.noOfPages = 3

        binding.viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator.onPageChange(position)
            }
        })


    }


}