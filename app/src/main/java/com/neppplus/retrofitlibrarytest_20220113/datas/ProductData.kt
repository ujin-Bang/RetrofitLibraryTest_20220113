package com.neppplus.retrofitlibrarytest_20220113.datas

import com.google.gson.annotations.SerializedName

class ProductData(

    var id: Int,
    var name: String,

    var Store: StoreData,
    @SerializedName("small_category")
    var smallCategory: SmallCategoryData

) {
}