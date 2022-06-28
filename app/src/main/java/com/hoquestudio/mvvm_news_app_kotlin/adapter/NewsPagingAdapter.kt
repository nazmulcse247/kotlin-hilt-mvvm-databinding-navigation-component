package com.hoquestudio.mvvm_news_app_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoquestudio.mvvm_news_app_kotlin.BR
import com.hoquestudio.mvvm_news_app_kotlin.R
import com.hoquestudio.mvvm_news_app_kotlin.databinding.ListItemBinding
import com.hoquestudio.mvvm_news_app_kotlin.models.Article

class NewsPagingAdapter : PagingDataAdapter<Article, NewsPagingAdapter.NewsViewHolder>(Diff_Util) {

    companion object {
        var Diff_Util = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }

    inner class NewsViewHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = getItem(position)
        holder.viewDataBinding.setVariable(BR._all,item)



        Glide.with(holder.viewDataBinding.root).load(item!!.urlToImage)
            .into(holder.viewDataBinding.root.findViewById(R.id.image))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPagingAdapter.NewsViewHolder {

        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }


}