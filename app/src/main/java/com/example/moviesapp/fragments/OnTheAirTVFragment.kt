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
import com.example.moviesapp.databinding.FragmentOnTheAirTVBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnTheAirTVFragment : Fragment() {

private lateinit var binding : FragmentOnTheAirTVBinding
    val args: OnTheAirTVFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_on_the_air_t_v,container,false)

        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title =
            getString(R.string.series_content_fragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val detailOnTheAirMovie = arguments?.let { OnTheAirTVFragmentArgs.fromBundle(it).onTheAirTVDetailArguments }

        val base= "https://image.tmdb.org/t/p/w500"

        var totalPic ="$base${detailOnTheAirMovie?.backdrop_path}"

        Picasso.Builder(binding.photoDetailTVOnTheAir.context).build()
            .load(totalPic.toUri()).into(binding.photoDetailTVOnTheAir)


        binding.detailTopMovie=detailOnTheAirMovie

        //to return back
        returnBack()
    }

    private fun returnBack(){
        binding.imageButtonOnTheAir.setOnClickListener {
            findNavController().navigate(R.id.action_onTheAirTVFragment_to_contentFragment)
        }
    }
}