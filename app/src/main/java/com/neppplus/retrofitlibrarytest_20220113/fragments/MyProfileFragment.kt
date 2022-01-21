package com.neppplus.retrofitlibrarytest_20220113.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
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
            alert.setTitle("닉네임 변경")

//            얼럿의 내부 뷰를 커스텀뷰로 (xml -> View로 가져와서)
//            xml 내부 UI접근 필요 -> inflate 해와서 사용하자

            val customView = LayoutInflater.from(mContext).inflate(R.layout.my_custom_alert_edit_nickname, null)

            alert.setView(customView)

            val edtNickname = customView.findViewById<EditText>(R.id.edtNickname)



            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

                val inputNickname = edtNickname.text.toString()

                Toast.makeText(mContext, inputNickname, Toast.LENGTH_SHORT).show()
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