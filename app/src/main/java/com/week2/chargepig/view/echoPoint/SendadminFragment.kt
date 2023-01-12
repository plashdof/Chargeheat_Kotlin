package com.week2.chargepig.view.echoPoint

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.week2.chargepig.App
import com.week2.chargepig.Retrofit
import com.week2.chargepig.Singleton
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

            // val filePath = getRealPathFromURIOld(Singleton.image)
            val filePath = getRealPathFromURI( App.context(),Singleton.image!!)
            val imageFile = createImageFile(filePath!!)
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

    private fun getRealPathFromURIOld(uri: Uri?): String {

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

    fun getRealPathFromURI(context: Context, uri: Uri): String? {

        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {

            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                return if ("primary".equals(type, ignoreCase = true)) {
                    (Environment.getExternalStorageDirectory().toString() + "/"
                            + split[1])
                } else {
                    val SDcardpath =
                        getRemovableSDCardPath(context).split("/Android").toTypedArray()[0]
                    SDcardpath + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(
                    context, contentUri, selection,
                    selectionArgs
                )
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
                context,
                uri,
                null,
                null
            )
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }


    fun getRemovableSDCardPath(context: Context?): String {
        val storages =
            ContextCompat.getExternalFilesDirs(App.context(), null) // todo
        return if (storages.size > 1 && storages[0] != null && storages[1] != null) storages[1].toString() else ""
    }


    fun getDataColumn(
        context: Context, uri: Uri?,
        selection: String?, selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.getContentResolver().query(
                uri!!, projection,
                selection, selectionArgs, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null) cursor.close()
        }
        return null
    }


    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri
            .authority
    }


    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri
            .authority
    }


    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri
            .authority
    }


    fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri
            .authority
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}