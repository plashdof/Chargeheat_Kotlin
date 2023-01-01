package com.week2.chargepig

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.week2.chargepig.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(){

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {


            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)

//            val buildLikeRetro = Retrofit.Builder()
//                .baseUrl("http://54.65.19.20:8080")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            val api = buildLikeRetro.create(TestAPI::class.java)
//            api.helloworld()
//                .enqueue(object: Callback<TestData> {
//                    override fun onResponse(
//                        call: Call<TestData>,
//                        response: Response<TestData>
//                    ) {
//                        Log.d("API결과","${response.body()}")
//                    }
//                    override fun onFailure(call: Call<TestData>, t: Throwable) {
//                        Log.d("API결과", "실패 : $t")
//                    }
//                })

        }

    }
}