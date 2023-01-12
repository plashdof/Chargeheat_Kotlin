package com.week2.chargepig.view.echoPoint

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.week2.chargepig.Singleton
import com.week2.chargepig.Retrofit
import com.week2.chargepig.databinding.FragmentSendadminBinding
import com.week2.chargepig.network.EchopointAPI
import com.week2.chargepig.network.models.EchopointData
import com.week2.chargepig.network.models.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val datas = EchopointData(Singleton.image.toString(), Singleton.name.toString(), Singleton.id.toString() )
            EchopointRetro.echopoint(datas)
                .enqueue(object: Callback<ResponseData> {
                    override fun onResponse(
                        call: Call<ResponseData>,
                        response: Response<ResponseData>
                    ) {
                        Log.d("API결과","${response.body()}")


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