package com.neppplus.retrofitlibrarytest_20220113.adapters

import android.content.Context
import android.os.Looper
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
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class MainRecyclerAdapter(val mContext: Context, val mList: List<ReviewData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    화면 상단에 보여줄 배너 목록을 담고 있는 ArryList
    val mBannerList = ArrayList<BannerData>()

//    상단 뷰페이저 어댑터 변수 -> 객체는 bind에서 생성
    lateinit var bannerViewPagerAdapter : BannerViewPagerAdapter

//    2가지 ViewHolder 필요함 => 0번칸: 상단부(Header) xml / 나머지(Item)칸: 리뷰모양 xml

    inner class HeaderViewHolder(row: View): RecyclerView.ViewHolder(row){

        val imgCategory1 = row.findViewById<ImageView>(R.id.imgCategory1)
        val bannerViewPager = row.findViewById<ViewPager>(R.id.bannerViewPager)

        fun bind() {

//            배너 페이지 어댑터를 생성
//            1.fm(FragmentManager) => 화면 mContext(Context) ->MainActivivy로 변신 -> supportFragmentManager
//            2.bannerList => Fragment에서 -> 배너 목록 API호출 -> 파싱된 것을 받아오자.
              bannerViewPagerAdapter = BannerViewPagerAdapter((mContext as MainActivity).supportFragmentManager, mBannerList)

            bannerViewPager.adapter = bannerViewPagerAdapter

//            완성된 배너 어댑터에 -> 2초마다 다음 그림으로 넘어가게

//            다음 배너로 넘어가게 (ViewPager에게 다음페이지로) => 할일 (코드)생성

//            시작은 0페이지에서
            var currentPage = 0

            val nextPage = {
//              다음쪽으로 , 페이지수 증가
                currentPage++

//                증가후 검사 -> 3장짜리인데, 3번칸으로 가게하면? 범위 벗어나는 에러
                if(currentPage == mBannerList.size){

//                    가야할 페이지가 3번칸이다 라면 => 처음으로 돌아가게 하자
                    currentPage = 0
                }

//                뷰페이저에 페이지 이동
                bannerViewPager.currentItem = currentPage

            } //Runnable : 할일이 담긴 변수

//            타이머 안에서 =>할일을 => UIThread로 전달해주는 도구.(Handler)
            val myHandler = android.os.Handler(Looper.getMainLooper())

//            Timer클래스 활용 => 할일 (코드)를 2초마다 반복

            val timer = Timer()
            timer.schedule( object : TimerTask() {
                override fun run() {

//                    반복 수행할 코드 => UI쓰레드가 아님(UI조작 / 앱 죽는다)
//                    UI쓰레드에다가 -> nextPage에 적힌 할일 실행하도록 넘겨주자.
                    myHandler.post(nextPage)

                }

            },2000,2000)


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