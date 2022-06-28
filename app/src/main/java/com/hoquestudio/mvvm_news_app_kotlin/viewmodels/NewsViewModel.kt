package com.hoquestudio.mvvm_news_app_kotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hoquestudio.mvvm_news_app_kotlin.models.Article
import com.hoquestudio.mvvm_news_app_kotlin.repository.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
@HiltViewModel
class NewsViewModel @Inject constructor(
    public val newsRepo: NewsRepo) : ViewModel() {

        val list : LiveData<PagingData<Article>> = newsRepo.getAllNewsStream()

}