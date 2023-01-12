package com.week2.chargepig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.week2.chargepig.view.qr.QrActivity
import com.week2.chargepig.view.qr.SuccessActivity
import com.week2.chargepig.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frag) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomnav = binding.mainBottomnav
        bottomnav.itemIconTintList = null


        binding.mainBottomnav.setupWithNavController(navController)

        // bottomnav 숨기는 로직
        navController.addOnDestinationChangedListener { _,destination,_ ->
            if(destination.id == R.id.homeFragment || destination.id == R.id.profileFragment){
                binding.bottomAppBar.visibility = View.VISIBLE
            }else{
                binding.bottomAppBar.visibility = View.GONE
            }
        }

    }









}