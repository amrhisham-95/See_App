package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.RecyclerViewCustom2Binding
import com.example.moviesapp.databinding.RecyclerViewCustom3Binding
import com.example.moviesapp.models.DetailsOfNowPlaying
import com.example.moviesapp.models.DetailsOfTopRated
import com.squareup.picasso.Picasso

class RecyclerAdapterNowPlayingDiffUtil(private val onRecyclerViewClickNowPlayingMovie: OnRecyclerViewClickNowPlayingMovie) :
    PagingDataAdapter<DetailsOfNowPlaying, RecyclerAdapterNowPlayingDiffUtil.MyViewHolder>(differCallback) {

    class MyViewHolder(var binding: RecyclerViewCustom3Binding) :
        RecyclerView.ViewHolder(binding.root)

    //By Using ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = RecyclerViewCustom3Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val currentItem = getItem(position)!!

            tVLanguage3.text = currentItem.original_language.uppercase()

            val base = "https://image.tmdb.org/t/p/w500"
            var totalPic = "$base${currentItem.poster_path}"

            Picasso.Builder(posterImageView3.context).build()
                .load(totalPic.toUri()).into(posterImageView3)

            customRecycler3.setOnClickListener {
                onRecyclerViewClickNowPlayingMovie.onClickedNowPlaying(position)
            }
        }
    }

  companion object{
      private val differCallback = object : DiffUtil.ItemCallback<DetailsOfNowPlaying>() {
          override fun areItemsTheSame(
              oldItem: DetailsOfNowPlaying,
              newItem: DetailsOfNowPlaying
          ): Boolean {
              return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(
              oldItem: DetailsOfNowPlaying,
              newItem: DetailsOfNowPlaying
          ): Boolean {
              return oldItem == newItem
          }

      }
  }

    //Fun used when select any item in paging recycler :
    fun currentSelected(pos:Int):DetailsOfNowPlaying?{
        return getItem(pos)
    }



}