package com.hoquestudio.mvvm_news_app_kotlin.di

import com.hoquestudio.mvvm_news_app_kotlin.models.NewsResponse
import com.hoquestudio.mvvm_news_app_kotlin.networks.ApiService
import com.hoquestudio.mvvm_news_app_kotlin.repository.NewsRepo
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object DIModule {

    @Singleton
    @Provides
    fun provideRetrofit () : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {

        return retrofit.create(ApiService::class.java)

    }

    @Singleton
    @Provides
    fun getNewsRepository(apiService: ApiService) : NewsRepo {
        return NewsRepo(apiService)
    }

}