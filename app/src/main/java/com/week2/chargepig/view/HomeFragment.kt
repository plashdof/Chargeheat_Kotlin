package com.week2.chargepig.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.week2.chargepig.*
import com.week2.chargepig.databinding.FragmentHomeBinding
import com.week2.chargepig.network.ProfileAPI
import com.week2.chargepig.network.models.ProfileData
import com.week2.chargepig.network.models.ResponseData
import com.week2.chargepig.view.adapter.HomeVPAdapter
import com.week2.chargepig.view.qr.QrActivity
import com.week2.chargepig.view.qr.SuccessActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(){
    private lateinit var binding : FragmentHomeBinding
    private lateinit var navController: NavController

    private var ProfileRetro = Retrofit.getInstance().create(ProfileAPI::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.btnFind.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_findFragment)
        }

        binding.btnEchopoint.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_echopointFragment)
        }

        binding.btnBluetooth.setOnClickListener {
            val intent = Intent(App.context(), BluetoothActivity::class.java)
            startActivity(intent)
        }

//        ProfileRetro.getprofile(Singleton.id)
//            .enqueue(object: Callback<ProfileData>{
//                override fun onResponse(call: Call<ProfileData>, response: Response<ProfileData>) {
//                    binding.tvEchopoint.text = "${response.body()!!.point.toString()}P"
//                }
//
//                override fun onFailure(call: Call<ProfileData>, t: Throwable) {}
//            })

        binding.btnQr.setOnClickListener {

            val Activity = activity as MainActivity

            val integrator = IntentIntegrator(Activity)
            integrator.setDesiredBarcodeFormats(ScanOptions.QR_CODE) // 여러가지 바코드중에 특정 바코드 설정 가능
            integrator.setPrompt("손난로 상단의 QR코드를 스캔해주세요") // 스캔할 때 하단의 문구
            integrator.setCameraId(0) // 0은 후면 카메라, 1은 전면 카메라
            integrator.setBeepEnabled(true) // 바코드를 인식했을 때 삑 소리유무
            integrator.setBarcodeImageEnabled(false) // 스캔 했을 때 스캔한 이미지 사용여부
            integrator.captureActivity = QrActivity::class.java
            activityResult.launch(integrator.createScanIntent()) // 스캔
        }

//        val adapter = HomeVPAdapter(datas)
//        binding.viewpager.adapter = adapter
//
//        // indicator 생성, viewpager 과 연결
//
//        val indicator = binding.indicator
//        indicator.noOfPages = 3
//
//        binding.viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
//
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//            }
//
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                indicator.onPageChange(position)
//            }
//        })


    }

    private val activityResult = this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        val data = result.data

        val intentResult: IntentResult? = IntentIntegrator.parseActivityResult(result.resultCode, data)
        if(intentResult != null){

            //QRCode Scan 성공
            if(intentResult.contents != null){
                //QRCode Scan result 있는경우
                Toast.makeText(App.context(), "인식된 QR-data: ${intentResult.contents}", Toast.LENGTH_SHORT).show()

                val intent = Intent(App.context(), SuccessActivity::class.java)
                startActivity(intent)
            }else{
                //QRCode Scan result 없는경우
                Toast.makeText(App.context(), "인식된 QR-data가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }else{
            //QRCode Scan 실패
            Toast.makeText(App.context(), "QR스캔에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }


}