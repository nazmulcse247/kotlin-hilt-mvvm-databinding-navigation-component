package com.hoquestudio.mvvm_news_app_kotlin.networks

import com.hoquestudio.mvvm_news_app_kotlin.models.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country : String,
        @Query("category") category : String,
        @Query("apiKey") apiKey : String,
        @Query("page") page : Int,
        @Query("pageSize") pageSize : Int

    ) : NewsResponse



}