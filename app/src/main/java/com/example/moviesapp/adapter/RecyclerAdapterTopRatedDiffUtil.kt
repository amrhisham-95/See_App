package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.RecyclerViewCustomBinding
import com.example.moviesapp.models.DetailsOfTopRated
import com.squareup.picasso.Picasso
import javax.inject.Inject

class RecyclerAdapterTopRatedDiffUtil @Inject constructor(private val onRecyclerViewClickTopRatedMovie: OnRecyclerViewClickTopRatedMovie) :
    PagingDataAdapter<DetailsOfTopRated,RecyclerAdapterTopRatedDiffUtil.MyViewHolder>(differCallback) {

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
            holder.setIsRecyclable(false)

            tVLanguage.text = currentItem.original_language.uppercase()
            tVDate.text = currentItem.release_date


            val base = "https://image.tmdb.org/t/p/w500"
            var totalPic = "$base${currentItem.poster_path}"

            Picasso.Builder(posterImageView.context).build()
                .load(totalPic.toUri()).into(posterImageView)

            customRecycler.setOnClickListener {
                onRecyclerViewClickTopRatedMovie.onClickedTopMovie(position)
            }
        }
    }


    companion object{
    private val differCallback = object : DiffUtil.ItemCallback<DetailsOfTopRated>() {
        override fun areItemsTheSame(
            oldItem: DetailsOfTopRated,
            newItem: DetailsOfTopRated
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DetailsOfTopRated,
            newItem: DetailsOfTopRated
        ): Boolean {
            return oldItem == newItem
        }

    }
}

    //Fun used when select any item in paging recycler :
    fun currentSelected(pos:Int):DetailsOfTopRated?{
        return getItem(pos)
    }

}