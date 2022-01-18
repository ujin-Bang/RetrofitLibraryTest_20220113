package com.neppplus.retrofitlibrarytest_20220113.api

import com.neppplus.retrofitlibrarytest_20220113.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface ServerAPIService {

//    로그인 기능 명세

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email:String,
        @Field("password") pw: String,
        ) : Call<BasicResponse>

//    회원가입 기능 명세

   @FormUrlEncoded
   @PUT("/user")
   fun putRequestSignUp(
       @Field("email") email: String,
       @Field("password") pw: String,
       @Field("nick_name") nick: String,
   ) : Call<BasicResponse>

//   중복환인 기능 - GET
   @GET("/user/check")
   fun getRequestDuplicatedCheck(
    @Query("type") type: String,
    @Query("value") value: String,

   ): Call<BasicResponse>

//   내 정보 조회 - GET / 토큰값(임시방안)
   @GET("user")
   fun getRequestMyInfo(
        @Header("X-Http-Token") token: String,
   ): Call<BasicResponse>

//   상품목록 받아오기 - GET / 아무 파라미터도 없음(서버의 임시 API)

   @GET("product")
   fun getRequestProductList() : Call<BasicResponse>

//   소분류 전체 목록 받아오기 - GET / 아무 파라미터 없어므(서버의 임시 API)
   @GET("category/small")
   fun getRequestSmallCategoryList(): Call<BasicResponse>
}