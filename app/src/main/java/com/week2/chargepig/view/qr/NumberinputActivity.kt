package com.week2.chargepig.view.qr

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.week2.chargepig.*
import com.week2.chargepig.databinding.ActivityNumberinputBinding
import com.week2.chargepig.network.RentAPI
import com.week2.chargepig.network.models.RentData
import com.week2.chargepig.network.models.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class NumberinputActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNumberinputBinding
    var num = ""

    private var RentRetro = Retrofit.getInstance().create(RentAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberinputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etNum.addTextChangedListener(object:TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                num = binding.etNum.text.toString()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.btn.setOnClickListener {
            Log.d("aaaaa", "$num")

            val data = RentData(num)

            if(num != "001024"){
                Toast.makeText(this@NumberinputActivity, "인식된 손난로가 없습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@NumberinputActivity, MainActivity::class.java)
                startActivity(intent)
            }else{
                RentRetro
                    .rent(data)
                    .enqueue(object : Callback<ResponseData> {
                        override fun onResponse(
                            call: Call<ResponseData>,
                            response: Response<ResponseData>
                        ) {
                            Log.d("API결과", "${response.body()}")
                            if(response.body()?.code == 300){
                                Toast.makeText(App.context(), "이미 대여중인 손난로입니다", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@NumberinputActivity, MainActivity::class.java)
                                startActivity(intent)
                            }else if(response.body()?.code == 200){
                                Toast.makeText(App.context(), "일련번호: 001024 대여 성공", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@NumberinputActivity, SuccessActivity::class.java)
                                startActivity(intent)
                            }
                        }

                        override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                            Log.d("API결과", "실패 : $t")
                        }
                    })
            }

//            if(num == "001024"){
//                if(Singleton.state){
//                    Toast.makeText(App.context(), "이미 대여중인 손난로입니다", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this@NumberinputActivity, MainActivity::class.java)
//                    startActivity(intent)
//                }else{
//                    Toast.makeText(App.context(), "일련번호: 001024 대여 성공", Toast.LENGTH_SHORT).show()
//                    Singleton.state = true
//                    val intent = Intent(this@NumberinputActivity, SuccessActivity::class.java)
//                    startActivity(intent)
//                }
//
//            }else{
//                Toast.makeText(this@NumberinputActivity, "인식된 손난로가 없습니다", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this@NumberinputActivity, MainActivity::class.java)
//                startActivity(intent)
//            }
        }

    }
}