package com.week2.chargepig

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.week2.chargepig.databinding.ActivityLoginBinding
import com.week2.chargepig.models.TestData
import com.week2.chargepig.retrofit.TestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity(){

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val buildLikeRetro = Retrofit.Builder()
                .baseUrl("http://54.180.134.41:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = buildLikeRetro.create(TestAPI::class.java)
            api.helloworld()
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