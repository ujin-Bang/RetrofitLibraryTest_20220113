package com.neppplus.retrofitlibrarytest_20220113.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.neppplus.retrofitlibrarytest_20220113.datas.BannerData
import com.neppplus.retrofitlibrarytest_20220113.fragments.BannerFragment

class BannerViewPagerAdapter(fm: FragmentManager, val mBannerList: List<BannerData>): FragmentPagerAdapter(fm) {
    override fun getCount()= mBannerList.size

    override fun getItem(position: Int): Fragment {

        return BannerFragment(mBannerList[position])


    }
}