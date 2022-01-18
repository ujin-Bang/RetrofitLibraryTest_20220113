package com.neppplus.retrofitlibrarytest_20220113.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.retrofitlibrarytest_20220113.R
import com.neppplus.retrofitlibrarytest_20220113.datas.SmallCategoryData

class CategoryRecyclerAdapter(
    val mContext: Context,
    val mList: List<SmallCategoryData>
    ): RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

//    클래스 내부의 또 다른 클래스(inner class)
    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

//  이 내부의 용도: xml -> UI 찾아내서 -> 데이터 반영 기능

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

     val row = LayoutInflater.from(mContext).inflate(R.layout.category_list_item, parent, false)
     return CategoryViewHolder(row)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

    }

    override fun getItemCount() = mList.size

}