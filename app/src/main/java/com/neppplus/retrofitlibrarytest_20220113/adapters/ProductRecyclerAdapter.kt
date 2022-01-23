package com.neppplus.retrofitlibrarytest_20220113.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.retrofitlibrarytest_20220113.EditReviewActivity
import com.neppplus.retrofitlibrarytest_20220113.R
import com.neppplus.retrofitlibrarytest_20220113.datas.ProductData

class ProductRecyclerAdapter(val mContext: Context, val mList: List<ProductData>): RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){

        val rootLayout = view.findViewById<LinearLayout>(R.id.rootLayout)
        val imgStoreLogo = view.findViewById<ImageView>(R.id.imgStoreLogo)
        val imgProductImg = view.findViewById<ImageView>(R.id.imgProductImg)
        val txtStoreName = view.findViewById<TextView>(R.id.txtStoreName)
        val txtProductName = view.findViewById<TextView>(R.id.txtProductName)
        val txtProductPrice = view.findViewById<TextView>(R.id.txtProductPrice)
        val btnWriteReview = view.findViewById<Button>(R.id.btnWriteReview)

        fun bind( data: ProductData) {

            txtStoreName.text = data.Store.name
            txtProductName.text = data.name

            Glide.with(mContext).load(data.imageURL).into(imgProductImg)
            Glide.with(mContext).load(data.Store.logoURL).into(imgStoreLogo)

            //  39800 -> 39,800 형태로 가공해 주자. 상품 데이터에 가격을 가공해주는 함수를 추가해 보자.
            txtProductPrice.text = data.getFormattedPrice()

            rootLayout.setOnClickListener {

                Toast.makeText(mContext, "${data.name}상품이 클릭됨", Toast.LENGTH_SHORT).show()

            }

            rootLayout.setOnLongClickListener {

                val alert = AlertDialog.Builder(mContext)
                alert.setTitle("상품 삭제")
                alert.setMessage("정말 해당 상품을 삭제하시겠습니까?")
                alert.setPositiveButton("확인",null)
                alert.setNegativeButton("취소",null)
                alert.show()

                return@setOnLongClickListener true
            }

            btnWriteReview.setOnClickListener {

//                리뷰 작성 화면 이동.
                val myIntent = Intent(mContext,EditReviewActivity::class.java)
                mContext.startActivity(myIntent)

            }



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