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
import com.example.moviesapp.databinding.FragmentDetailTVTopRatedBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedDetailTVFragment : Fragment() {
    private lateinit var binding : FragmentDetailTVTopRatedBinding
    val args: TopRatedDetailTVFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_detail_t_v_top_rated,container,false)
        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title =
            getString(R.string.series_content_fragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val detailTopRatedTVMovie = arguments?.let { TopRatedDetailTVFragmentArgs.fromBundle(it).topRatedTVArguments }

        val base= "https://image.tmdb.org/t/p/w500"

        var totalPic ="$base${detailTopRatedTVMovie?.backdrop_path}"

        Picasso.Builder(binding.photoDetailTV.context).build()
            .load(totalPic.toUri()).into(binding.photoDetailTV)


        binding.detailTopMovie=detailTopRatedTVMovie

        //to return back
        returnBack()
    }

    private fun returnBack(){
        binding.imageButtonTopRatedTV.setOnClickListener {
            findNavController().navigate(R.id.action_detailTVFragment_to_contentFragment)
        }
    }

}