package com.hoquestudio.mvvm_news_app_kotlin.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.hoquestudio.mvvm_news_app_kotlin.models.Article
import com.hoquestudio.mvvm_news_app_kotlin.networks.ApiService
import com.hoquestudio.mvvm_news_app_kotlin.pagin.NewsPaginSource

class NewsRepo(val apiService: ApiService)  {

    fun getAllNewsStream() : LiveData<PagingData<Article>> = Pager(
        config = PagingConfig(
            20,
            5,
            false
        ),
        pagingSourceFactory = {
            NewsPaginSource(apiService)
        }
    ).liveData
}