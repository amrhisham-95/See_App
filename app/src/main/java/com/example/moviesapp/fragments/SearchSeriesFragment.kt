package com.example.moviesapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.activities.ContentActivity
import com.example.moviesapp.activities.SearchActivity
import com.example.moviesapp.adapter.*
import com.example.moviesapp.databinding.FragmentSearchSeriesBinding
import com.example.moviesapp.models.DetailsOfSearching
import com.example.moviesapp.ui.DrawerLocker
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.viewModels.RetrofitViewModel
import com.example.moviesapp.viewModels.RoomViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchSeriesFragment : Fragment() , OnRecyclerViewSearchingTV {

    private lateinit var binding : FragmentSearchSeriesBinding

    private val retrofitViewModel : RetrofitViewModel by viewModels()

    private val roomViewModel : RoomViewModel by viewModels()

    private lateinit var recyclerViewTVSearchingAdapter : RecyclerAdapterTVSearching


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_search_series,container,false)

        //to hide navigation drawer from fragment you want to hide
        (activity as DrawerLocker?)!!.setDrawerLocked(false)

        //to open toggle from the layout toolbar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //to appear toggle first with 3 vertical bars
        (activity as SearchActivity).getToggleSync()

        //to put title on toolbar
        (activity as SearchActivity).supportActionBar?.title = "TV Search"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = retrofitViewModel
        binding.roomViewModel = roomViewModel

        binding.btnSearchTV.setOnClickListener {

            if(binding.searchEditTextSeries.text.isEmpty()){
                Toast.makeText(requireContext(),"Enter Your Series",Toast.LENGTH_LONG).show()
            }else{
                //search Movie
                retrofitViewModel.getRetrofitViewModelDetailsOfSpecificTVFromService(Constants.API_KEY,binding.searchEditTextSeries.text.toString())

                retrofitViewModel.mutableLiveDataSearchingTV.observe(viewLifecycleOwner) {
                    if (it.toData()?.results?.isNotEmpty() == true) {
                        roomViewModel.addDataSearchingTVViewModel(it.toData()!!.results)
                    }
                }

                roomViewModel.liveDataSearchingTV.observe(viewLifecycleOwner){
                    binding.apply {

                        recyclerViewTVSearchingAdapter = RecyclerAdapterTVSearching(this@SearchSeriesFragment)
                        recyclerViewTVSearching.adapter=recyclerViewTVSearchingAdapter
                        recyclerViewTVSearching.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,true)
                        recyclerViewTVSearching.setHasFixedSize(true)

                        recyclerViewTVSearchingAdapter.differ.submitList(it)


                    }
                }

            }

        }

        binding.imageButtonBackTV.setOnClickListener {
            val intent = Intent(activity,ContentActivity::class.java)
            startActivity(intent)
        }
        }

    override fun onClickedSearchingTV(position: Int) {

        binding.containerLayoutTV.visibility= View.VISIBLE
        binding.recyclerViewTVSearching.visibility=View.GONE
        binding.photoDetailSearchTV.visibility=View.VISIBLE
        binding.btnSearchTV.visibility=View.GONE
        binding.btnGoSearchTV.visibility=View.VISIBLE

        roomViewModel.liveDataSearchingTV.observe(viewLifecycleOwner) {
            val base = "https://image.tmdb.org/t/p/w500"
            var totalPic = "$base${it[position].backdrop_path}"

            Picasso.Builder(binding.photoDetailSearchTV.context).build()
                .load(totalPic.toUri()).into(binding.photoDetailSearchTV)

            binding.apply {
                overviewDetailSearchTV.text = it[position].overview
                dateDetailSearchTV.text = it[position].first_air_date
                titleDetailSearchTV.text = it[position].original_name
                languageDetailSearchTV.text = it[position].original_language
            }
        }
        binding.btnGoSearchTV.setOnClickListener {
            roomViewModel.deleteAllDetailsSearchingTVViewModel()
            findNavController().navigate(R.id.action_searchMovieFragment_to_searchSeriesFragment)
        }
        binding.imageButtonBackTV.setOnClickListener {
            roomViewModel.deleteAllDetailsSearchingTVViewModel()
            val intent = Intent(activity,ContentActivity::class.java)
            startActivity(intent)
        }

    }


}



