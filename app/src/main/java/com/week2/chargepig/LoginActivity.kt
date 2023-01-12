package com.week2.chargepig

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AlertDialog
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

            if(ID.isBlank()) {
                val builder = AlertDialog.Builder(this@LoginActivity)
                builder.setTitle("")
                    .setMessage("아이디를 입력하세요")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                builder.show()
            } else if(PW.isBlank()){
                val builder = AlertDialog.Builder(this@LoginActivity)
                builder.setTitle("")
                    .setMessage("비밀번호를 입력하세요")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                builder.show()
            }else{
                LoginRetro
                    .login(data)
                    .enqueue(object: Callback<ResponseData> {
                        override fun onResponse(
                            call: Call<ResponseData>,
                            response: Response<ResponseData>
                        ) {
                            Log.d("API결과","${response.body()}")

                            Singleton.id = ID

                            if(response.body()?.code == 200){
                                val builder = AlertDialog.Builder(this@LoginActivity)
                                builder.setTitle("")
                                    .setMessage("${ID}(주민) 님 환영합니다!")
                                    .setPositiveButton("확인",
                                        DialogInterface.OnClickListener { dialog, id ->
                                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                            startActivity(intent)
                                        })
                                builder.show()
                            }else if(response.body()?.code == 201){
                                val builder = AlertDialog.Builder(this@LoginActivity)
                                builder.setTitle("")
                                    .setMessage("${ID}(사원) 님 환영합니다!")
                                    .setPositiveButton("확인",
                                        DialogInterface.OnClickListener { dialog, id ->
                                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                            startActivity(intent)
                                        })
                                builder.show()
                            }else{
                                val builder = AlertDialog.Builder(this@LoginActivity)
                                builder.setTitle("")
                                    .setMessage("존재하지 않는 계정입니다")
                                    .setPositiveButton("확인",
                                        DialogInterface.OnClickListener { dialog, id ->
                                        })
                                builder.show()
                            }


                        }
                        override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                            Log.d("API결과", "실패 : $t")
                        }
                    })
            }
        }

    }
}