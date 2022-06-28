package com.hoquestudio.mvvm_news_app_kotlin.models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)