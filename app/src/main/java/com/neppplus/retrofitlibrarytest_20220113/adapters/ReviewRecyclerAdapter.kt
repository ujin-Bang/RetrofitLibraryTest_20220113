package com.neppplus.retrofitlibrarytest_20220113.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20220113.datas.ReviewData

class ReviewRecyclerAdapter(
    val mContext: Context,
    val mList: List<ReviewData>): RecyclerView.Adapter<ReviewRecyclerAdapter.ReviewViewHolder>() {

    inner class ReviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {

    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }
}