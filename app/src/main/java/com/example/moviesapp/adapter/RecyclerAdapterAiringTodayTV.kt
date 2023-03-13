package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.RecyclerViewCustom2Binding
import com.example.moviesapp.models.DetailsOfAiringTodayTV
import com.example.moviesapp.models.DetailsOfNowPlaying
import com.squareup.picasso.Picasso


class RecyclerAdapterAiringTodayTV(private val onRecyclerViewClickAiringTodayTV: OnRecyclerViewClickAiringTodayTV) :
    PagingDataAdapter<DetailsOfAiringTodayTV, RecyclerAdapterAiringTodayTV.MyViewHolder>(
        differCallback
    ){

    class MyViewHolder(var binding: RecyclerViewCustom2Binding) :
        RecyclerView.ViewHolder(binding.root)

    //By Using ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = RecyclerViewCustom2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val currentItem = getItem(position)!!

            tVLanguage2.text = currentItem.original_language.uppercase()

            val base = "https://image.tmdb.org/t/p/w500"
            var totalPic = "$base${currentItem.poster_path}"

            Picasso.Builder(posterImageView2.context).build()
                .load(totalPic.toUri()).into(posterImageView2)

            customRecycler2.setOnClickListener {
                onRecyclerViewClickAiringTodayTV.onClickedAiringTodayTV(position)
            }
        }
    }

    companion object{

        private val differCallback = object : DiffUtil.ItemCallback<DetailsOfAiringTodayTV>() {
            override fun areItemsTheSame(
                oldItem: DetailsOfAiringTodayTV,
                newItem: DetailsOfAiringTodayTV
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DetailsOfAiringTodayTV,
                newItem: DetailsOfAiringTodayTV
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    //Fun used when select any item in paging recycler :
    fun currentSelected(pos:Int):DetailsOfAiringTodayTV?{
        return getItem(pos)
    }
}