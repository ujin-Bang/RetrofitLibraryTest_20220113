package com.neppplus.retrofitlibrarytest_20220113

import android.content.Context

class ContextUtil {

    companion object {

//  어떤 파일명? 파일 명
        private val prefName = "RetrofitTestPref"

//        어떤 데이터 항목? 항목 명
        private val TOKEN = "TOKEN"

//        저장함수 (setter) / 조회함수(getter)
        fun setToken(context : Context, token: String) {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(TOKEN,token).apply()
        }

        fun getToken(context: Context): String {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(TOKEN, "")!!

        }

    }

}