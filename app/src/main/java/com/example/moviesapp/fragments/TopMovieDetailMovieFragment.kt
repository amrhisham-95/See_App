package com.example.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.R
import com.example.moviesapp.activities.ContentActivity
import com.example.moviesapp.databinding.FragmentDetailMovieTopMovieBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopMovieDetailMovieFragment : Fragment() {

    private lateinit var binding : FragmentDetailMovieTopMovieBinding

    val args: TopMovieDetailMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_detail_movie_top_movie,container,false)

        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title = getString(R.string.movies_content_fragment)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val detailTopMovie = arguments?.let { TopMovieDetailMovieFragmentArgs.fromBundle(it).topRatedMovieArguments }

        val base= "https://image.tmdb.org/t/p/w500"

        var totalPic ="$base${detailTopMovie?.backdrop_path}"

        Picasso.Builder(binding.photoDetailMovie.context).build()
            .load(totalPic.toUri()).into(binding.photoDetailMovie)


        binding.detailTopMovie=detailTopMovie

        //to return back
        returnBack()
    }


    private fun returnBack(){
        binding.imageButtonTopRatedMovie.setOnClickListener {
            findNavController().navigate(R.id.action_detailMovieFragment_to_contentFragment)
        }
    }

}