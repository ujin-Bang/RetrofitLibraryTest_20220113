package com.neppplus.retrofitlibrarytest_20220113.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20220113.R
import com.neppplus.retrofitlibrarytest_20220113.databinding.FragmentBennerBinding
import com.neppplus.retrofitlibrarytest_20220113.datas.BannerData

class BannerFragment(val mBannerData: BannerData):BaseFragment() {

    lateinit var binding: FragmentBennerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_benner, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.imgBanner.setOnClickListener {

//            배너 클릭하면 -> 인터넷 링크(Intent-4)

            val myUri = Uri.parse(mBannerData.clickUrl)
            val myIntent = Intent(Intent.ACTION_VIEW, myUri)
            startActivity(myIntent)
        }

    }

    override fun setValues() {

//        프래그먼트를 만들 때 받아온 배너 데이터 -> 그림 표시
        Glide.with(mContext).load(mBannerData.displayImageUrl).into(binding.imgBanner)

    }
}