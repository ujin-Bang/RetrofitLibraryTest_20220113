package com.neppplus.retrofitlibrarytest_20220113.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

//    Retrofit 타입 객체 하나만 생성 -> 모두가 공유

    companion object {
//        기본 접속 서버
        private var BASE_URL = "http://3.34.159.73"

      private var retrofit : Retrofit? = null //앱이 처음 켜질때는 없다. => 한번만 만들고 함수를 통해서 공유

        fun getRetrofit() : Retrofit {

            if (retrofit == null){
//                통신 담당 객체를 만들지 ㅇ낳았다면 => 없을때만 새로 한번 만들자.

                retrofit =Retrofit.Builder()
                    .baseUrl(BASE_URL) //어느 서버로 접속?
                    .addConverterFactory(GsonConverterFactory.create()) // 파싱을 자동 도구로 활용
                    .build()
            }

            return retrofit!!

        }
    }

}