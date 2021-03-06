package com.neppplus.retrofitlibrarytest_20220113.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SmallCategoryData(

    var id: Int,
    var name: String,
    @SerializedName("large_category_id")
    var largeategoryid: Int

): Serializable {
}