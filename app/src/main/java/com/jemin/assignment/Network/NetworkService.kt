package com.jemin.assignment.Network

import com.jemin.assignment.Network.Get.Response.GetMovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkService {

    // 영화 검색 API
    @GET("/v1/search/movie.json")
    fun getMovieSearch(
            @Header("X-Naver-Client-Id") clientId: String,
            @Header("X-Naver-Client-Secret") secret: String,
            @Query("query") query : String,
            @Query("display") display : Int
    ) : Call<GetMovieListResponse>
}