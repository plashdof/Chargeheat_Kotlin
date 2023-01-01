package com.week2.chargepig.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.week2.chargepig.R
import com.week2.chargepig.databinding.FragmentHomeBinding

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

    }


}