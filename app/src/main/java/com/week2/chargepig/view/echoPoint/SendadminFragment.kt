package com.week2.chargepig.view.echoPoint

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.week2.chargepig.Singleton
import com.week2.chargepig.Retrofit
import com.week2.chargepig.databinding.FragmentSendadminBinding
import com.week2.chargepig.network.EchopointAPI
import com.week2.chargepig.network.models.ResponseData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

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

            val filePath = getRealPathFromURI(Singleton.image)
            val imageFile = createImageFile(filePath)
            val requestFile = RequestBody.create(MediaType.parse("image/*"),imageFile)
            val body = MultipartBody.Part.createFormData("image","img" ,requestFile)

            Log.d("aaaaa","${body.toString()}")
            Log.d("aaaaa","${Singleton.id} ${Singleton.name}")

            EchopointRetro.echopoint(Singleton.id,body,Singleton.name)
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

    //  Uri -> local 파일 path 생성

    private fun getRealPathFromURI(uri: Uri?): String {

        var columnIndex = 0
        var proj = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = activity?.contentResolver?.query(uri!!, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        return cursor.getString(columnIndex)
    }


    // local 파일 path -> 이미지 file 생성

    private fun createImageFile(path: String): File {
        // 사진이 저장될 폴더 있는지 체크
        var file = File(Environment.getExternalStorageDirectory(), path)
        if (!file.exists()) file.mkdir()

        var imageName = "fileName.jpeg"
        var imageFile = File("${Environment.getExternalStorageDirectory().absoluteFile}/path/", "$imageName")

        return imageFile
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}