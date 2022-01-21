package com.neppplus.retrofitlibrarytest_20220113.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20220113.utils.ContextUtil
import com.neppplus.retrofitlibrarytest_20220113.R
import com.neppplus.retrofitlibrarytest_20220113.databinding.FragmentMyProfileBinding
import com.neppplus.retrofitlibrarytest_20220113.datas.BasicResponse
import com.neppplus.retrofitlibrarytest_20220113.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment:BaseFragment() {

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

    override fun setupEvents() {
        binding.btnEditNickname.setOnClickListener {

//            닉네임 변경 입력(AlertDialog 커스텀뷰) + API 호출
            val alert = AlertDialog.Builder(mContext)
            alert.setMessage("닉네임 변경")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

            })
            alert.setNegativeButton("취소", null)
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