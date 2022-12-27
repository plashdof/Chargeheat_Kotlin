package com.week2.chargepig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.week2.chargepig.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frag) as NavHostFragment
        val navController = navHostFragment.navController


        binding.mainBottomnav.setupWithNavController(navController)

        binding.mainBtnQr.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // 여러가지 바코드중에 특정 바코드 설정 가능
            integrator.setPrompt("손난로 상단의 QR코드를 스캔해주세요") // 스캔할 때 하단의 문구
            integrator.setCameraId(0) // 0은 후면 카메라, 1은 전면 카메라
            integrator.setBeepEnabled(true) // 바코드를 인식했을 때 삑 소리유무
            integrator.setBarcodeImageEnabled(false) // 스캔 했을 때 스캔한 이미지 사용여부
            integrator.captureActivity = QrActivity::class.java
            integrator.initiateScan() // 스캔
        }

        val options = ScanOptions()
        barcodeLauncher.launch(options)
    }

    private val barcodeLauncher = registerForActivityResult(ScanContract()){result->

        if(result.contents != null){
            Toast.makeText(this@MainActivity, "${result.contents}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            if (result.contents != null) {
                Log.e("INFO: ", "${result.contents}")
            } else {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }








}