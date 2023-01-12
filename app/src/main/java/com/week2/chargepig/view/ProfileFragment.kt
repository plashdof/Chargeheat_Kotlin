package com.week2.chargepig.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.week2.chargepig.Retrofit
import com.week2.chargepig.Singleton
import com.week2.chargepig.databinding.FragmentProfileBinding
import com.week2.chargepig.network.ProfileAPI
import com.week2.chargepig.network.models.ProfileData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding


    private var ProfileRetro = Retrofit.getInstance().create(ProfileAPI::class.java)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        ProfileRetro.getprofile(Singleton.id)
            .enqueue(object : Callback<ProfileData>{
                override fun onResponse(call: Call<ProfileData>, response: Response<ProfileData>) {
                    Log.d("API결과", "${response.body()}")
                    binding.tvId.text = response.body()?.id
                    binding.tvPw.text = response.body()?.pw
                    binding.tvName.text = response.body()?.name
                    binding.tvDong.text = response.body()?.dong.toString()
                    binding.tvHosu.text = response.body()?.hosu.toString()
                    binding.tvRentalnum.text = response.body()?.rentalnum.toString()
                    binding.tvMypoint.text = response.body()?.point.toString()

                }

                override fun onFailure(call: Call<ProfileData>, t: Throwable) {
                }
            })

    }

}