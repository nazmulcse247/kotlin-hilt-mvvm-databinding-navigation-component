package com.hoquestudio.mvvm_news_app_kotlin.pagin

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hoquestudio.mvvm_news_app_kotlin.models.Article
import com.hoquestudio.mvvm_news_app_kotlin.networks.ApiService
import retrofit2.HttpException


const val STARTING_INDEX = 1

class NewsPaginSource(val apiService: ApiService) : PagingSource<Int,Article>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key?: STARTING_INDEX

        return try {
            val data = apiService.getAllNews("us","business","be38c02294d24017ab484317d87fddbb",position, params.loadSize)
            LoadResult.Page(
                data = data.articles,
                prevKey = if (params.key == STARTING_INDEX) null else position-1,
                nextKey = if (data.articles.isEmpty()) null else position + 1
            )
        }
        catch (e : Exception){

            LoadResult.Error(e)
        }
        catch (http : HttpException){

            LoadResult.Error(http)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }


}