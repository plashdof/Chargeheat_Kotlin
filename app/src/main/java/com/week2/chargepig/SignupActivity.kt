package com.week2.chargepig

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.week2.chargepig.databinding.ActivitySignupBinding
import com.week2.chargepig.network.LoginAPI
import com.week2.chargepig.network.SignupAPI
import com.week2.chargepig.network.models.ResponseData
import com.week2.chargepig.network.models.SignupData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding
    private val SignupRetro = Retrofit.getInstance().create(SignupAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {

            val datas = SignupData(binding.etId.text.toString(), binding.etPw.text.toString(),binding.etName.text.toString(),
            binding.etDong.text.toString().toInt(), binding.etHosu.text.toString().toInt())

            SignupRetro
                .signup(datas)
                .enqueue(object: Callback<ResponseData> {
                    override fun onResponse(
                        call: Call<ResponseData>,
                        response: Response<ResponseData>
                    ) {
                        Log.d("API결과","${response.body()}")

                        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        Log.d("API결과", "실패 : $t")
                    }
                })


        }
    }


}