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
import com.example.moviesapp.databinding.FragmentAiringTodayTVBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AiringTodayTVFragment : Fragment() {
 private lateinit var binding: FragmentAiringTodayTVBinding
    val args: AiringTodayTVFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_airing_today_t_v,container,false)

        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title =
            getString(R.string.series_content_fragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val detailAiringTodayTVMovie = arguments?.let { AiringTodayTVFragmentArgs.fromBundle(it).airingTodayTVDetailArguments }

        val base= "https://image.tmdb.org/t/p/w500"

        var totalPic ="$base${detailAiringTodayTVMovie?.backdrop_path}"

        Picasso.Builder(binding.photoDetailTVAiring.context).build()
            .load(totalPic.toUri()).into(binding.photoDetailTVAiring)


        binding.detailTopMovie=detailAiringTodayTVMovie

        //to return back
        returnBack()
    }

    private fun returnBack(){
        binding.imageButtonAiringToday.setOnClickListener {
            findNavController().navigate(R.id.action_airingTodayTVFragment_to_contentFragment)
        }
    }

}