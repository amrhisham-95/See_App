package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.RecyclerViewCustom2Binding
import com.example.moviesapp.databinding.RecyclerViewCustomBinding
import com.example.moviesapp.models.DetailsOfSearching
import com.example.moviesapp.models.DetailsOfTopRatedTV
import com.squareup.picasso.Picasso


class RecyclerAdapterMovieSearching (private val onRecyclerViewClickSearching: OnRecyclerViewClickSearching) : RecyclerView.Adapter<RecyclerAdapterMovieSearching.MyViewHolder>() {

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
            val currentItem = differ.currentList[position]

            tVLanguage2.text= currentItem.original_language.uppercase()

            val base= "https://image.tmdb.org/t/p/w500"
            var totalPic = "$base${currentItem.poster_path}"

            Picasso.Builder(posterImageView2.context).build()
                .load(totalPic.toUri()).into(posterImageView2)

            customRecycler2.setOnClickListener {
                onRecyclerViewClickSearching.onClickedSearching(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object  : DiffUtil.ItemCallback<DetailsOfSearching>(){
        override fun areItemsTheSame(oldItem: DetailsOfSearching, newItem: DetailsOfSearching): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: DetailsOfSearching, newItem: DetailsOfSearching): Boolean {
            return oldItem==newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)



}