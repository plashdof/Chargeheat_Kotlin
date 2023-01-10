package com.week2.chargepig

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.week2.chargepig.databinding.ActivityLoginBinding
import com.week2.chargepig.network.LoginAPI
import com.week2.chargepig.network.TestAPI
import com.week2.chargepig.network.models.LoginData
import com.week2.chargepig.network.models.ResponseData
import com.week2.chargepig.network.models.TestData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(){

    private lateinit var binding : ActivityLoginBinding

    private val TestRetro = Retrofit.getInstance().create(TestAPI::class.java)
    private val LoginRetro = Retrofit.getInstance().create(LoginAPI::class.java)

    var ID = 0
    var PW = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etId.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                ID = binding.etId.text.toString().toInt()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etPassword.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                PW = binding.etPassword.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {

            var data = LoginData(ID,PW)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

//            CoroutineScope(Dispatchers.IO).launch{
//                val TestResult = TestRetro.helloworld()
//
//                Log.d("aaaaa", "${TestResult.toString()}")
//                val LoginResult = LoginRetro.login(TestResult)
//
//                Log.d("aaaaa", "${LoginResult.toString()}")
//            }



//            Log.d("API결과","${data.id}, ${data.password}")
//            Retrofit.buildLikeRetro
//                .create(LoginAPI::class.java)
//                .login(data)
//                .enqueue(object: Callback<ResponseData> {
//                    override fun onResponse(
//                        call: Call<ResponseData>,
//                        response: Response<ResponseData>
//                    ) {
//                        Log.d("API결과","${response.body()}")
//                    }
//                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
//                        Log.d("API결과", "실패 : $t")
//                    }
//                })
        }

    }
}