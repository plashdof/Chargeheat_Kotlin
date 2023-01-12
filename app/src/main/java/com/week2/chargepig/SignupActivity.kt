package com.week2.chargepig

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.week2.chargepig.databinding.ActivitySignupBinding
import com.week2.chargepig.network.IdcheckAPI
import com.week2.chargepig.network.LoginAPI
import com.week2.chargepig.network.SignupAPI
import com.week2.chargepig.network.models.IdcheckData
import com.week2.chargepig.network.models.ResponseData
import com.week2.chargepig.network.models.SignupData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding
    private val SignupRetro = Retrofit.getInstance().create(SignupAPI::class.java)
    private val IdcheckRetro = Retrofit.getInstance().create(IdcheckAPI::class.java)

    private var id = ""
    private var pw = ""
    private var name = ""
    private var dong = ""
    private var hosu = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로가기 버튼
        
        binding.btnBack.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Id 중복체크 버튼
        
        binding.btnIdcheck.setOnClickListener {

            val data = IdcheckData(binding.etId.text.toString())
            IdcheckRetro
                .idcheck(data)
                .enqueue(object: Callback<ResponseData> {
                    override fun onResponse(
                        call: Call<ResponseData>,
                        response: Response<ResponseData>
                    ) {
                        if(response.body()?.code == 1){
                            Log.d("API결과","사용가능한 아이디 입니다")

                            val builder = AlertDialog.Builder(this@SignupActivity)
                            builder.setTitle("")
                                .setMessage("사용 가능한 아이디 입니다.")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener{dialog, id->
                                    })
                            builder.show()


                        }else{
                            Log.d("API결과","이미 존재하는 아이디 입니다")

                            val builder = AlertDialog.Builder(this@SignupActivity)
                            builder.setTitle("")
                                .setMessage("이미 존재하는 아이디 입니다.")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener{dialog, id->
                                    })
                            builder.show()

                        }

                    }
                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        Log.d("API결과", "실패 : $t")
                    }
                })


        }

        // 회원가입 버튼 활성화 /비활성화

        binding.etId.addTextChangedListener(object:TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                id = binding.etId.text.toString()

                if(id.isNotBlank() && pw.isNotBlank() && name.isNotBlank() && dong.isNotBlank() && hosu.isNotBlank()){
                    binding.btnSignup.setTextColor(Color.rgb(255,255,255))
                }else{
                    binding.btnSignup.setBackgroundColor(Color.rgb(0,0,0))
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        binding.etPw.addTextChangedListener(object:TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pw = binding.etPw.text.toString()

                if(id.isNotBlank() && pw.isNotBlank() && name.isNotBlank() && dong.isNotBlank() && hosu.isNotBlank()){
                    binding.btnSignup.setTextColor(Color.rgb(255,255,255))
                }else{
                    binding.btnSignup.setBackgroundColor(Color.rgb(0,0,0))
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        binding.etName.addTextChangedListener(object:TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                name = binding.etName.text.toString()

                if(id.isNotBlank() && pw.isNotBlank() && name.isNotBlank() && dong.isNotBlank() && hosu.isNotBlank()){
                    binding.btnSignup.setTextColor(Color.rgb(255,255,255))
                }else{
                    binding.btnSignup.setBackgroundColor(Color.rgb(0,0,0))
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        binding.etHosu.addTextChangedListener(object:TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                hosu = binding.etHosu.text.toString()

                if(id.isNotBlank() && pw.isNotBlank() && name.isNotBlank() && dong.isNotBlank() && hosu.isNotBlank()){
                    binding.btnSignup.setTextColor(Color.rgb(255,255,255))
                }else{
                    binding.btnSignup.setBackgroundColor(Color.rgb(0,0,0))
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        binding.etDong.addTextChangedListener(object:TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                dong = binding.etDong.text.toString()
                if(id.isNotBlank() && pw.isNotBlank() && name.isNotBlank() && dong.isNotBlank() && hosu.isNotBlank()){
                    binding.btnSignup.setTextColor(Color.rgb(255,255,255))
                }else{
                    binding.btnSignup.setBackgroundColor(Color.rgb(0,0,0))
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })


        // 회원가입 버튼

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

                        val builder = AlertDialog.Builder(this@SignupActivity)
                        builder.setTitle("")
                            .setMessage("회원가입 성공.")
                            .setPositiveButton("확인",
                                DialogInterface.OnClickListener{dialog, id->
                                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                })
                        builder.show()


                    }
                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        Log.d("API결과", "실패 : $t")
                    }
                })


        }
    }


}