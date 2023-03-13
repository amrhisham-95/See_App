package com.example.moviesapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.activities.ContentActivity
import com.example.moviesapp.adapter.*
import com.example.moviesapp.databinding.FragmentContentMovieBinding
import com.example.moviesapp.ui.DrawerLocker
import com.example.moviesapp.ui.TopRatedLoadStateAdapter
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.viewModels.RetrofitViewModel
import com.example.moviesapp.viewModels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentMovieFragment : Fragment(), OnRecyclerViewClickTopRatedMovie,
    OnRecyclerViewClickNowPlayingMovie, OnRecyclerViewClickUpComingMovie {

    private lateinit var binding: FragmentContentMovieBinding

    private val retrofitViewModel: RetrofitViewModel by viewModels()

    private val roomViewModel: RoomViewModel by viewModels()


    private val recyclerAdapter by lazy {RecyclerAdapterTopRatedDiffUtil(this)}
    private val recyclerAdapter2 by  lazy { RecyclerAdapterNowPlayingDiffUtil(this) }
    private val recyclerAdapter3 by lazy {RecyclerAdapterUpComingDiffUtil(this)  }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_content_movie, container, false)

        //to hide navigation drawer from fragment you want to hide
        (activity as DrawerLocker?)!!.setDrawerLocked(false)

        //to open toggle from the layout toolbar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //to appear toggle first with 3 vertical bars
        (activity as ContentActivity).getToggleSync()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = retrofitViewModel
        binding.roomViewModel = roomViewModel

        initAdapterNowPlayingMovie()
        initAdapterTopRatedMovie()
        initAdapterUpComingMovie()


        topRatedMoviesWithPaginationDirectlyFromNetwork()

        nowPlayingMoviesWithPaginationDirectlyFromNetwork()

        upComingMoviesWithPaginationDirectlyFromNetwork()
    }


    //to hide navigation drawer from fragment you want to hide
    override fun onDestroy() {
        super.onDestroy()
        (activity as DrawerLocker?)!!.setDrawerLocked(true)
    }

    override fun onClickedNowPlaying(position: Int) {
        Toast.makeText(requireContext(), "NowPlayingMovie", Toast.LENGTH_LONG).show()

        var result2 = recyclerAdapter2.currentSelected(position)
            findNavController().navigate(
                ContentFragmentDirections.actionContentFragmentToNowPlayingMovieDetailFragment(
                    result2!!
                )
            )

    }

    override fun onClickedTopMovie(position: Int) {
        Toast.makeText(requireContext(), "TopRatedMovie", Toast.LENGTH_LONG).show()

        var result1 = recyclerAdapter.currentSelected(position)

        findNavController().navigate(
            ContentFragmentDirections.actionContentFragmentToDetailMovieFragment(
                result1!!
            )
        )
    }

    override fun onClickedUpComing(position: Int) {
        Toast.makeText(requireContext(), "UpComingMovie", Toast.LENGTH_LONG).show()
        var result3 = recyclerAdapter3.currentSelected(position)
            findNavController().navigate(
                ContentFragmentDirections.actionContentFragmentToUpComingDetailMovieFragment(
                    result3!!
                )
            )

    }

    private fun initAdapterTopRatedMovie(){

        //to put progress bar and retry button inside the recycler view
        binding.topRatedMovieRecyclerView.adapter = recyclerAdapter.withLoadStateHeaderAndFooter(
            header = TopRatedLoadStateAdapter{recyclerAdapter.retry()},
            footer = TopRatedLoadStateAdapter{recyclerAdapter.retry()}
        )

        //to put progress bar and retry button outside the recycler view until the data displays in recycler view
       recyclerAdapter.addLoadStateListener { loadState ->
           binding.topRatedMovieRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
           binding.statusLoadingWheelTopRated.isVisible =loadState.source.refresh is LoadState.Loading
           binding.imageView2TopRated.isVisible = loadState.source.refresh is LoadState.Error
           handleError(loadState)
       }

        binding.imageView2TopRated.setOnClickListener {
            recyclerAdapter.retry()
        }
    }

    private fun initAdapterNowPlayingMovie(){
        recyclerAdapter2.addLoadStateListener { loadState ->
            binding.nowPlayingMovieRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.statusLoadingWheelNowPlaying.isVisible =loadState.source.refresh is LoadState.Loading
            binding.imageView2NowPlaying.isVisible = loadState.source.refresh is LoadState.Error
            handleError(loadState)
        }

        binding.imageView2NowPlaying.setOnClickListener {
            recyclerAdapter2.retry()
        }
    }

    private fun initAdapterUpComingMovie(){
        recyclerAdapter3.addLoadStateListener { loadState ->
            binding.upComingMovieRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.statusLoadingWheelUpComing.isVisible =loadState.source.refresh is LoadState.Loading
            binding.imageView2UpComing.isVisible = loadState.source.refresh is LoadState.Error
            handleError(loadState)
        }

        binding.imageView2UpComing.setOnClickListener {
            recyclerAdapter3.retry()
        }
    }

    private fun handleError(loadState: CombinedLoadStates) {
val errorState = loadState.source.append as? LoadState.Error
    ?: loadState.source.append as? LoadState.Error

        errorState?.let {
            Toast.makeText(requireContext(),"${it.error}",Toast.LENGTH_LONG).show()
        }

    }

    private fun topRatedMoviesWithPaginationDirectlyFromNetwork(){
        //top Rated movies with pagination from network directly
        binding.apply {
            topRatedMovieRecyclerView.adapter = recyclerAdapter
            topRatedMovieRecyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true)
        }
        lifecycleScope.launchWhenCreated {
            retrofitViewModel.movieTopRatedList.collect {
                recyclerAdapter.submitData(it)
            }
        }
    }

    private fun nowPlayingMoviesWithPaginationDirectlyFromNetwork(){
        //now Playing with pagination from network directly
        binding.apply {
            nowPlayingMovieRecyclerView.adapter = recyclerAdapter2
            nowPlayingMovieRecyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)

            lifecycleScope.launchWhenCreated {
                retrofitViewModel.movieNowPlayingMoviesList.collect {
                    recyclerAdapter2.submitData(it)
                }
            }
        }
    }

    private fun upComingMoviesWithPaginationDirectlyFromNetwork(){
        //up Coming with pagination from network directly
        binding.apply {
            upComingMovieRecyclerView.adapter = recyclerAdapter3
            upComingMovieRecyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)

            lifecycleScope.launchWhenCreated {
                retrofitViewModel.movieUpComingList.collect {
                    recyclerAdapter3.submitData(it)
                }
            }
        }
    }
}