package com.example.moviesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ItemLoadStateBinding

class TopRatedLoadStateViewHolder(private val binding: ItemLoadStateBinding, retry: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

        init{
            binding.retryBtn.setOnClickListener { retry.invoke() }
        }

    fun bind (loadState: LoadState){
        if(loadState is LoadState.Error){
            binding.errorMsg.text=loadState.error.localizedMessage
        }
        binding.progressBar.isVisible =loadState is LoadState.Loading
        binding.retryBtn.isVisible= loadState is LoadState.Loading
        binding.errorMsg.isVisible = loadState is LoadState.Loading
    }

    companion object{
        fun create(parent : ViewGroup,retry: () -> Unit):TopRatedLoadStateViewHolder{
            val view =LayoutInflater.from(parent.context).inflate(R.layout.item_load_state,parent,false)
            val binding = ItemLoadStateBinding.bind(view)
            return TopRatedLoadStateViewHolder(binding,retry)
        }
    }
}