package com.neppplus.retrofitlibrarytest_20220113.datas

import com.google.gson.annotations.SerializedName
import java.text.NumberFormat
import java.util.*

class ProductData(

    var id: Int,
    var name: String,
    var price: Int,
    @SerializedName("img_url")
    var imageURL: String,

    var Store: StoreData,
    @SerializedName("small_category")
    var smallCategory: SmallCategoryData

) {
    fun getFormattedPrice(): String {

        return "${ NumberFormat.getNumberInstance(Locale.KOREA).format(this.price) }원"

    }

}