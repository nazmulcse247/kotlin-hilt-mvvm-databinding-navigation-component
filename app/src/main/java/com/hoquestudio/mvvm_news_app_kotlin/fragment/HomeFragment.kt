package com.hoquestudio.mvvm_news_app_kotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hoquestudio.mvvm_news_app_kotlin.R
import com.hoquestudio.mvvm_news_app_kotlin.adapter.NewsPagingAdapter
import com.hoquestudio.mvvm_news_app_kotlin.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment2 : Fragment() {

     val viewModel by viewModels<NewsViewModel>()
     val newsPagingAdapter = NewsPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.list.observe(viewLifecycleOwner){
            newsPagingAdapter.submitData(lifecycle,it)
        }

        newsPagingAdapter.addLoadStateListener { state->
            when(state.refresh){
                is LoadState.Loading ->{
                    view.findViewById<ProgressBar>(R.id.news_progress).visibility = View.VISIBLE
                }

                is LoadState.NotLoading ->{
                    view.findViewById<ProgressBar>(R.id.news_progress).visibility = View.GONE
                }

                is LoadState.Error ->{
                    view.findViewById<ProgressBar>(R.id.news_progress).visibility = View.GONE
                    Toast.makeText(requireContext(),"Eroor occered",Toast.LENGTH_LONG).show()
                }
            }

        }

        view.findViewById<RecyclerView>(R.id.news_recycler).adapter = newsPagingAdapter


    }

}