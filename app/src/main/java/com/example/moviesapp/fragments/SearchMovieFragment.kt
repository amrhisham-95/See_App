package com.example.moviesapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.moviesapp.databinding.FragmentSearchMovieBinding
import com.example.moviesapp.ui.DrawerLocker
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.viewModels.RetrofitViewModel
import com.example.moviesapp.viewModels.RoomViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieFragment : Fragment(), OnRecyclerViewClickSearching {
   private lateinit var binding : FragmentSearchMovieBinding

    private val retrofitViewModel : RetrofitViewModel by viewModels()
    private val roomViewModel : RoomViewModel by viewModels()

    private lateinit var recyclerViewMovieSearching : RecyclerAdapterMovieSearching

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_search_movie,container,false)

        //to hide navigation drawer from fragment you want to hide
        (activity as DrawerLocker?)!!.setDrawerLocked(false)

        //to open toggle from the layout toolbar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //to appear toggle  with 3 vertical bars
        (activity as SearchActivity).getToggleSync()

        //to put title on toolbar
        (activity as SearchActivity).supportActionBar?.title = "Movies Search"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = retrofitViewModel
        binding.roomViewModel = roomViewModel


        binding.btnSearchMovie.setOnClickListener {

            if(binding.searchEditText.text.isEmpty()){
                Toast.makeText(requireContext(),"Enter Your Movie",Toast.LENGTH_LONG).show()
            }else{
                //search Movie
                retrofitViewModel.getRetrofitViewModelDetailsOfSpecificFromService(Constants.API_KEY,binding.searchEditText.text.toString())

                retrofitViewModel.mutableLiveDataSearching.observe(viewLifecycleOwner) {
                    if (it.toData()?.results?.isNotEmpty() == true) {
                        roomViewModel.addDataSearchingViewModel(it.toData()!!.results)
                    }
                }

               roomViewModel.liveDataSearching.observe(viewLifecycleOwner){
                    binding.apply {

                        recyclerViewMovieSearching = RecyclerAdapterMovieSearching(this@SearchMovieFragment)
                        recyclerViewSearchMovie.adapter=recyclerViewMovieSearching
                        recyclerViewSearchMovie.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,true)
                        recyclerViewSearchMovie.setHasFixedSize(true)

                        recyclerViewMovieSearching.differ.submitList(it)


                    }
                }

            }
        }


    }

    override fun onClickedSearching(position: Int) {

        binding.containerLayoutMovie.visibility= View.VISIBLE
        binding.recyclerViewSearchMovie.visibility=View.GONE
        binding.photoDetailSearchMovie.visibility=View.VISIBLE
        binding.btnSearchMovie.visibility=View.GONE
        binding.btnGoSearchMovie.visibility=View.VISIBLE

        roomViewModel.liveDataSearching.observe(viewLifecycleOwner) {
            val base = "https://image.tmdb.org/t/p/w500"
            var totalPic = "$base${it[position].backdrop_path}"

            Picasso.Builder(binding.photoDetailSearchMovie.context).build()
                .load(totalPic.toUri()).into(binding.photoDetailSearchMovie)

            binding.apply {
                overviewDetailSearchMovie.text = it[position].overview
                dateDetailSearchMovie.text = it[position].release_date
                titleDetailSearchMovie.text = it[position].original_title
                languageDetailSearchMovie.text = it[position].original_language
            }
        }
        binding.btnGoSearchMovie.setOnClickListener {
            roomViewModel.deleteAllDetailsSearchingTVViewModel()
            findNavController().navigate(R.id.action_searchMovieFragment_to_searchSeriesFragment)
        }
        binding.imageButtonBackMovie.setOnClickListener {
            roomViewModel.deleteAllDetailsSearchingTVViewModel()
            val intent = Intent(activity,ContentActivity::class.java)
            startActivity(intent)
        }


    }
}