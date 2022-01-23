package com.neppplus.retrofitlibrarytest_20220113.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StoreData(

    var id:Int,
    var name: String,
    @SerializedName("Logo_url")
    var logoURL: String,

):Serializable {
}