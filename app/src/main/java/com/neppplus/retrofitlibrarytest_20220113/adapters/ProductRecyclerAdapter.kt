package com.neppplus.retrofitlibrarytest_20220113.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20220113.R
import com.neppplus.retrofitlibrarytest_20220113.datas.ProductData

class ProductRecyclerAdapter(val mContext: Context, val mList: List<ProductData>): RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){

        val imgStoreLogo = view.findViewById<ImageView>(R.id.imgStoreLogo)
        val imgProductImg = view.findViewById<ImageView>(R.id.imgProductImg)
        val txtStoreName = view.findViewById<TextView>(R.id.txtStoreName)
        val txtProductName = view.findViewById<TextView>(R.id.txtProductName)
        val txtProductPrice = view.findViewById<TextView>(R.id.txtProductPrice)

        fun bind( data: ProductData) {

            txtStoreName.text = data.Store.name
            txtProductName.text = data.name

            //  39800 -> 39,800 형태로 가공해 주자. 상품 데이터에 가격을 가공해주는 함수를 추가해 보자.
            txtProductPrice.text = data.getFormattedPrice()


           Glide.with(mContext).load(data.imageURL).into(imgProductImg)
            Glide.with(mContext).load(data.Store.logoURL).into(imgStoreLogo)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(row)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bind( mList[position])

    }

    override fun getItemCount() = mList.size

}