package com.neppplus.retrofitlibrarytest_20220113.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.neppplus.retrofitlibrarytest_20220113.fragments.MyProfileFragment
import com.neppplus.retrofitlibrarytest_20220113.fragments.ProductListFragment
import com.neppplus.retrofitlibrarytest_20220113.fragments.ReviewListFragment

class MainViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {

        return when(position) {
             0 -> "리뷰목록"
            1 -> "상품목록"
            else -> "소중한 나 프로플"
        }
    }

    override fun getItem(position: Int): Fragment {

        return when(position){

            0 -> ReviewListFragment()
            1 -> ProductListFragment()
            else -> MyProfileFragment()

        }


    }
}