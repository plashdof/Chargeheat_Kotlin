package com.week2.chargepig.view.qr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.week2.chargepig.databinding.ActivityNumberinputBinding

class NumberinputActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNumberinputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberinputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)
        }
    }
}