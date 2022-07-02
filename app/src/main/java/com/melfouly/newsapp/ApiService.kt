package com.melfouly.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/top-headlines?apiKey=c1d49986a4cb46769b3765ce95fe0295")
    fun getNews(@Query("category") cat: String, @Query("country") code: String): Call<NewsModel>

}