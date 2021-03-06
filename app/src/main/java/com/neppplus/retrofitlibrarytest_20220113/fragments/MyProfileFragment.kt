package com.neppplus.retrofitlibrarytest_20220113.fragments

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.neppplus.retrofitlibrarytest_20220113.utils.ContextUtil
import com.neppplus.retrofitlibrarytest_20220113.R
import com.neppplus.retrofitlibrarytest_20220113.databinding.FragmentMyProfileBinding
import com.neppplus.retrofitlibrarytest_20220113.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20220113.utils.GlobalData
import com.neppplus.retrofitlibrarytest_20220113.utils.URIPathHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MyProfileFragment:BaseFragment() {

    val REQ_FOR_GALLERY = 1000

    lateinit var binding : FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_profile,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQ_FOR_GALLERY) {

            if(resultCode == Activity.RESULT_OK) {

//                ????????? ????????? ????????????
                val selectedImageUri = data?.data!!

                Log.d("??????????????????URI",selectedImageUri.toString())

//                Uri-> ?????? ?????? ????????? ????????? ???????????? ???.
//                Uri-> File ????????? ??????. -> ??? ?????? ????????? ????????????, Retrofit??? ????????? ??? ?????? ???.

                val file = File(URIPathHelper().getPath(mContext, selectedImageUri))

//                ????????? -> Retrofit??? ?????? ????????? RequestBody ????????? ??????
                val fileReqBody = RequestBody.create(MediaType.get("image/*"), file)

//                ?????? ?????? ???????????? ??????
                 val body = MultipartBody.Part.createFormData("profile_image","myFile.jpg",fileReqBody)

                apiService.putRequestProfileImg(body).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {

                        if(response.isSuccessful) {
                            Toast.makeText(mContext, "????????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show()

//                            ???????????? ????????? ??????(selectedImageUri)??? ????????? ?????? ?????? ??????

                            Glide.with(mContext).load(selectedImageUri).into(binding.imgProfile)

                        }
                        else {

                            Toast.makeText(mContext, "????????? ?????? ????????? ??????????????????.", Toast.LENGTH_SHORT)
                                .show()

                        }


                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }


                })
            }
        }

    }

    override fun setupEvents() {

        binding.imgProfile.setOnClickListener {

//            ?????? ?????? ?????? ?????? ?????? ??????.(???????????? ????????????)
                val pl = object : PermissionListener{
                    override fun onPermissionGranted() {

                        //            ?????????(????????????????????? ??????) ??? ?????? ????????? ??????(????????????)
                        val myIntent = Intent()
                        myIntent.action = Intent.ACTION_PICK
                        myIntent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
                        startActivityForResult(myIntent,REQ_FOR_GALLERY)
                    }

                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                        Toast.makeText(mContext, "????????? ??????????????? ???????????????", Toast.LENGTH_SHORT).show()
                    }

                }

            TedPermission.create()
                .setPermissionListener( pl )
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()

        }

        binding.btnEditNickname.setOnClickListener {

//            ????????? ?????? ??????(AlertDialog ????????????) + API ??????
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("????????? ??????")

//            ????????? ?????? ?????? ??????????????? (xml -> View??? ????????????)
//            xml ?????? UI?????? ?????? -> inflate ????????? ????????????

            val customView = LayoutInflater.from(mContext).inflate(R.layout.my_custom_alert_edit_nickname, null)

            alert.setView(customView)

            val edtNickname = customView.findViewById<EditText>(R.id.edtNickname)



            alert.setPositiveButton("??????", DialogInterface.OnClickListener { dialogInterface, i ->

                val inputNickname = edtNickname.text.toString()

                apiService.patchRequestEditUserInfo("nickname",inputNickname).enqueue(object :Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if(response.isSuccessful){
                            
                            val br = response.body()!!
                            
//                            ????????? ?????? - > ?????? ??????.
                            val token = br.data.token
                            
                            ContextUtil.setToken(mContext, token)

                            Toast.makeText(mContext, "????????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show()

                            getMyInfoFromServer()
                            
                        }
                        else {

//                            ????????? ?????? ?????? -> ?????? ?????? ??????
                            val jsonObj = JSONObject( response.errorBody()!!.string())
                            Log.e("????????? ?????? ??????", jsonObj.toString())

                            val message = jsonObj.getString()
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                        
                    }

                })

            })
            alert.setNegativeButton("??????", null)
            alert.show()
        }

    }

    override fun setValues() {

        getMyInfoFromServer()

        binding.txtNickname.text = GlobalData.loginUser!!.nickname
        Glide.with(mContext).load(GlobalData.loginUser!!.profileImageURL).into(binding.imgProfile)

        when (GlobalData.loginUser!!.provider) {

            "facbook" -> {
                binding.imgPovider.setImageResource(R.drawable.facebook_logo)
                binding.imgPovider.visibility = View.VISIBLE

            }
            "kakao" -> {
                binding.imgPovider.setImageResource(R.drawable.kakao_logo)
                binding.imgPovider.visibility = View.VISIBLE

            }
            else -> {
                binding.imgPovider.visibility = View.GONE
            }

        }
    }

    fun getMyInfoFromServer( ){

        apiService.getRequestMyInfo().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if(response.isSuccessful){

                    val br = response.body()!!

                    binding.txtNickname.text = br.data.user.nickname
                    Glide.with(mContext).load(br.data.user.profileImageURL).into(binding.imgProfile)


                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }
}