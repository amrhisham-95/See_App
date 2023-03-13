package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.RecyclerViewCustomBinding
import com.example.moviesapp.models.DetailsOfNowPlaying
import com.example.moviesapp.models.DetailsOfTopRatedTV
import com.squareup.picasso.Picasso


class RecyclerAdapterTopRatedTV(private val onRecyclerViewClickTopRatedTV: OnRecyclerViewClickTopRatedTV) :
    PagingDataAdapter<DetailsOfTopRatedTV, RecyclerAdapterTopRatedTV.MyViewHolder>(
       differCallback
    ) {

    class MyViewHolder(var binding: RecyclerViewCustomBinding) :
        RecyclerView.ViewHolder(binding.root)

    //By Using ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = RecyclerViewCustomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val currentItem = getItem(position)!!

            tVLanguage.text = currentItem.original_language.uppercase()
            tVDate.text = currentItem.first_air_date

            val base = "https://image.tmdb.org/t/p/w500"
            var totalPic = "$base${currentItem.poster_path}"

            Picasso.Builder(posterImageView.context).build()
                .load(totalPic.toUri()).into(posterImageView)

            customRecycler.setOnClickListener {
                onRecyclerViewClickTopRatedTV.onClickedTopRatedTV(position)
            }
        }
    }


  companion object{
      private val differCallback = object : DiffUtil.ItemCallback<DetailsOfTopRatedTV>() {
          override fun areItemsTheSame(
              oldItem: DetailsOfTopRatedTV,
              newItem: DetailsOfTopRatedTV
          ): Boolean {
              return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(
              oldItem: DetailsOfTopRatedTV,
              newItem: DetailsOfTopRatedTV
          ): Boolean {
              return oldItem == newItem
          }

      }
  }

    //Fun used when select any item in paging recycler :
    fun currentSelected(pos: Int): DetailsOfTopRatedTV? {
        return getItem(pos)
    }


}