package com.neppplus.retrofitlibrarytest_20220113.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20220113.R
import com.neppplus.retrofitlibrarytest_20220113.datas.ReviewData

class MainRecyclerAdapter(val mContext: Context, val mList: List<ReviewData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    2가지 ViewHolder 필요함 => 0번칸: 상단부(Header) xml / 나머지(Item)칸: 리뷰모양 xml

    inner class HeaderViewHolder(row: View): RecyclerView.ViewHolder(row){


    }

    inner class ItemViewHolder(row: View): RecyclerView.ViewHolder(row){

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

    }

    override fun getItemCount() = mList.size +1

}