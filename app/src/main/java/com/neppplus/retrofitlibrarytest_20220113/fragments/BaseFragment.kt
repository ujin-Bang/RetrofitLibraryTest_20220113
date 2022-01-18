package com.neppplus.retrofitlibrarytest_20220113.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.neppplus.retrofitlibrarytest_20220113.api.ServerAPI
import com.neppplus.retrofitlibrarytest_20220113.api.ServerAPIService

abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context

//    상속시켜줄 것 : API호출 명세를 담은 변수
    lateinit var apiService: ServerAPIService

   override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

       val retrofit = ServerAPI.getRetrofit()
       apiService = retrofit.create(ServerAPIService::class.java)
    }


   abstract fun setupEvents()
   abstract fun setValues()

}