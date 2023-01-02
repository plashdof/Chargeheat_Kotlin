package com.week2.chargepig

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.week2.chargepig.databinding.ActivityLoginBinding
import com.week2.chargepig.network.TestAPI
import com.week2.chargepig.network.models.TestData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(){

    private lateinit var binding : ActivityLoginBinding
    var ID = ""
    var PW = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etId.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                ID = binding.etId.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.btnLogin.setOnClickListener {

            Retrofit.buildLikeRetro
                .create(TestAPI::class.java)
                .helloworld()
                .enqueue(object: Callback<TestData> {
                    override fun onResponse(
                        call: Call<TestData>,
                        response: Response<TestData>
                    ) {
                        Log.d("API결과","${response.body()}")
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    override fun onFailure(call: Call<TestData>, t: Throwable) {
                        Log.d("API결과", "실패 : $t")
                    }
                })
        }

    }
}