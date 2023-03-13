package com.example.moviesapp.ui

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class TopRatedLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<TopRatedLoadStateViewHolder>(){
    override fun onBindViewHolder(holder: TopRatedLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): TopRatedLoadStateViewHolder {
      return  TopRatedLoadStateViewHolder.create(parent,retry)
    }

}