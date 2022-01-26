package com.neppplus.retrofitlibrarytest_20220113.datas

import com.google.gson.annotations.SerializedName

class BannerData(
    var id: Int,
    @SerializedName("img_url")
    var displayImageUrl: String,
    @SerializedName("click_url")
    var clickUrl: String

) {

}