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
import com.example.moviesapp.databinding.FragmentNowPlayingMovieDetailBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingMovieDetailFragment : Fragment() {

private lateinit var binding : FragmentNowPlayingMovieDetailBinding
    val args: NowPlayingMovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_now_playing_movie_detail,container,false)

        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title = getString(R.string.movies_content_fragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val detailNowPlayingMovie = arguments?.let { NowPlayingMovieDetailFragmentArgs.fromBundle(it).nowPlayingMovieDetailArguments }

        val base= "https://image.tmdb.org/t/p/w500"

        var totalPic ="$base${detailNowPlayingMovie?.backdrop_path}"

        Picasso.Builder(binding.photoDetailMovieNowPlaying.context).build()
            .load(totalPic.toUri()).into(binding.photoDetailMovieNowPlaying)

        binding.detailTopMovie=detailNowPlayingMovie

        //to return back
        returnBack()
    }

    private fun returnBack(){
        binding.imageButtonNowPlaying.setOnClickListener {
            findNavController().navigate(R.id.action_nowPlayingMovieDetailFragment_to_contentFragment)
        }
    }

}