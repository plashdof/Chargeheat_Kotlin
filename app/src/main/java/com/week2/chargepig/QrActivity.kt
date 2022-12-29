package com.week2.chargepig

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.week2.chargepig.databinding.ActivityQrBinding

class QrActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrBinding
    private lateinit var capture: CaptureManager
    var flashstate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val barcodeView = binding.decoratedBarcodeView


        // CaptureManager에 context와 xml에서 적용한 레이아웃을 넣어줍니다.
        capture = CaptureManager(this, binding.decoratedBarcodeView)
        // intent와 savedInstanceState를 넣어줍니다.
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.decode() //decode

        binding.btnExit.setOnClickListener {
            onBackPressed()
        }

        binding.btnFlash.setOnClickListener {
            if(flashstate){
                barcodeView.setTorchOff()
                flashstate = false
            }else {
                barcodeView.setTorchOn()
                flashstate = true
            }
        }

        binding.btnNumber.setOnClickListener {
            val intent = Intent(this, NumberinputActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }
    override fun onPause() {
        super.onPause()
        capture.onPause()
    }
    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }
}