package com.week2.chargepig

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.week2.chargepig.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var binding : FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
}