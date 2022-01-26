package com.neppplus.retrofitlibrarytest_20220113.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20220113.MainActivity
import com.neppplus.retrofitlibrarytest_20220113.R
import com.neppplus.retrofitlibrarytest_20220113.datas.BannerData
import com.neppplus.retrofitlibrarytest_20220113.datas.ReviewData

class MainRecyclerAdapter(val mContext: Context, val mList: List<ReviewData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    화면 상단에 보여줄 배너 목록을 담고 있는 ArryList
    val mBannerList = ArrayList<BannerData>()

//    2가지 ViewHolder 필요함 => 0번칸: 상단부(Header) xml / 나머지(Item)칸: 리뷰모양 xml

    inner class HeaderViewHolder(row: View): RecyclerView.ViewHolder(row){

        val imgCategory1 = row.findViewById<ImageView>(R.id.imgCategory1)
        val bannerViewPager = row.findViewById<ViewPager>(R.id.bannerViewPager)

        fun bind() {

//            배너 페이지 어댑터를 생성
//            1.fm(FragmentManager) => 화면 mContext(Context) ->MainActivivy로 변신 -> supportFragmentManager
//            2.bannerList => Fragment에서 -> 배너 목록 API호출 -> 파싱된 것을 받아오자.
             val bannerViewPagerAdapter = BannerViewPagerAdapter((mContext as MainActivity).supportFragmentManager, mBannerList)

            bannerViewPager.adapter = bannerViewPagerAdapter

            imgCategory1.setOnClickListener {
                Toast.makeText(mContext, "1번카테고리 눌림", Toast.LENGTH_SHORT).show()
            }
        }


    }

    inner class ItemViewHolder(row: View): RecyclerView.ViewHolder(row){
        val txtReviewProductName = row.findViewById<TextView>(R.id.txtReviewProductName)
        val txtReviewerName = row.findViewById<TextView>(R.id.txtReviewerName)
        val imgProdcutImg = row.findViewById<TextView>(R.id.imgProdcutImg)

        fun bind(data: ReviewData) {

            txtReviewProductName.text = data.product.name
            txtReviewerName.text = data.user.nickname

//            Glide.with(mContext).load()

        }

    }

//    position별로 어떤 모양이 나가야 하는지(viewType이 어떻게 되는지 ) 알려줄 함수

    val HEADER_VIEW_TYPE = 1000
    val REVIEW_ITEM_TYPE = 1001

    override fun getItemViewType(position: Int): Int {

//        position 0: 맨 윗칸 => 상단 뷰
//        position 그 외: 목록 표시. => 리뷰 아이템

        return when(position ) {

            0 -> HEADER_VIEW_TYPE
                else -> REVIEW_ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {

            HEADER_VIEW_TYPE -> {
                val row = LayoutInflater.from(mContext).inflate(R.layout.main_recycler_item_top_view,parent,false)
                HeaderViewHolder(row)
            }
            else -> {
//                리뷰아이템
                val row= LayoutInflater.from(mContext).inflate(R.layout.main_recycler_item_review_item, parent, false)
                ItemViewHolder(row)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when( holder ) {

            is HeaderViewHolder ->{

                holder.bind()

            }
            is ItemViewHolder -> {

//                리뷰 아이템 바인딩
                holder.bind( mList[position-1] )
            }

        }
    }

    override fun getItemCount() = mList.size +1

}