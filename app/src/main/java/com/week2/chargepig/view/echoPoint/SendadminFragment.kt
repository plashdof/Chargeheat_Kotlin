package com.week2.chargepig.view.echoPoint

import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.week2.chargepig.App
import com.week2.chargepig.Retrofit
import com.week2.chargepig.Singleton
import com.week2.chargepig.databinding.FragmentSendadminBinding
import com.week2.chargepig.network.EchopointAPI
import com.week2.chargepig.network.models.EchopointData
import com.week2.chargepig.network.models.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class SendadminFragment : Fragment() {

    private var _binding : FragmentSendadminBinding? = null
    private val binding get() = _binding!!

    private val EchopointRetro = Retrofit.getInstance().create(EchopointAPI::class.java)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendadminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBackground.setImageURI(Singleton.image)
        binding.btnComplete.setOnClickListener {


            val byteArrayOutputStream = ByteArrayOutputStream()

            // uri 을 bitmap 으로 변환
            val bitmap = MediaStore.Images.Media.getBitmap(App.context().contentResolver, Singleton.image)
            
            // bitmap 사이즈 줄이기
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            
            // bitmap 을 bytearray로 변환
            val imageBytes: ByteArray = byteArrayOutputStream.toByteArray()

            // base64 string 으로 변환
            val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)

            val datas = EchopointData(Singleton.name, imageString)

            Log.d("aaaaa","${datas.toString()}")
            Log.d("aaaaa","${Singleton.id}")
            EchopointRetro.echopoint(Singleton.id, datas)
                .enqueue(object : Callback<ResponseData> {
                    override fun onResponse(
                        call: Call<ResponseData>,
                        response: Response<ResponseData>
                    ) {
                        Log.d("API결과", "${response.body()}")
                    }

                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        Log.d("API결과", "실패 : $t")
                    }
                })
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}