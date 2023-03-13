package com.example.moviesapp.fragments

import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.activities.ContentActivity
import com.example.moviesapp.adapter.*
import com.example.moviesapp.databinding.FragmentContentSeriesBinding
import com.example.moviesapp.ui.DrawerLocker
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.viewModels.RetrofitViewModel
import com.example.moviesapp.viewModels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentSeriesFragment : Fragment(), OnRecyclerViewClickTopRatedTV,
    OnRecyclerViewClickOnTheAirTV, OnRecyclerViewClickAiringTodayTV {

    private lateinit var binding: FragmentContentSeriesBinding

    private val retrofitViewModel: RetrofitViewModel by viewModels()

    private val roomViewModel: RoomViewModel by viewModels()

    private val recyclerAdapterTV by lazy { RecyclerAdapterTopRatedTV(this) }
    private val recyclerAdapter2TV by lazy { RecyclerAdapterOnTheAirTV(this)}
    private val recyclerAdapter3TV by lazy { RecyclerAdapterAiringTodayTV(this)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_content_series, container, false)

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


        initAdapterOnTheAirTV()
        initAdapterAiringTV()
        initAdapterTopRatedTV()


        topRatedTVWithPaginationDirectlyFromNetwork()
        onTheAirTVWithPaginationDirectlyFromNetwork()
        airingTodayTVWithPaginationDirectlyFromNetwork()
    }


    //to hide navigation drawer from fragment you want to hide
    override fun onDestroy() {
        super.onDestroy()
        (activity as DrawerLocker?)!!.setDrawerLocked(true)
    }

    override fun onClickedAiringTodayTV(position: Int) {
        Toast.makeText(requireContext(), "AiringTV", Toast.LENGTH_LONG).show()

        var result6 = recyclerAdapter3TV.currentSelected(position)

        findNavController().navigate(
            ContentFragmentDirections.actionContentFragmentToAiringTodayTVFragment(
                result6!!
            )
        )

    }

    override fun onClickedOnTheAirTV(position: Int) {
        Toast.makeText(requireContext(), "OnTheAirTV", Toast.LENGTH_LONG).show()

        var result5 = recyclerAdapter2TV.currentSelected(position)

        findNavController().navigate(
            ContentFragmentDirections.actionContentFragmentToOnTheAirTVFragment(
                result5!!
            )
        )

    }

    override fun onClickedTopRatedTV(position: Int) {
        Toast.makeText(requireContext(), "TopRatedTV", Toast.LENGTH_LONG).show()

        var result4 = recyclerAdapterTV.currentSelected(position)

        findNavController().navigate(
            ContentFragmentDirections.actionContentFragmentToDetailTVFragment(
                result4!!
            )
        )
    }

    private fun initAdapterTopRatedTV(){
        recyclerAdapterTV.addLoadStateListener { loadState ->
            binding.topRatedTVRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.statusLoadingWheelTopRatedTV.isVisible =loadState.source.refresh is LoadState.Loading
            binding.imageView2TopRatedTV.isVisible = loadState.source.refresh is LoadState.Error
            handleError(loadState)
        }

        binding.imageView2TopRatedTV.setOnClickListener {
            recyclerAdapterTV.retry()
        }
    }

    private fun initAdapterOnTheAirTV(){
        recyclerAdapter2TV.addLoadStateListener { loadState ->
            binding.onTheAirTVRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.statusLoadingWheelOnTheAir.isVisible =loadState.source.refresh is LoadState.Loading
            binding.imageView2OnTheAir.isVisible = loadState.source.refresh is LoadState.Error
            handleError(loadState)
        }

        binding.imageView2OnTheAir.setOnClickListener {
            recyclerAdapter2TV.retry()
        }
    }

    private fun initAdapterAiringTV(){
        recyclerAdapter3TV.addLoadStateListener { loadState ->
            binding.airingTodayTVRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.statusLoadingWheelAiringToday.isVisible =loadState.source.refresh is LoadState.Loading
            binding.imageView2AiringToday.isVisible = loadState.source.refresh is LoadState.Error
            handleError(loadState)
        }

        binding.imageView2AiringToday.setOnClickListener {
            recyclerAdapter3TV.retry()
        }
    }

    private fun handleError(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.append as? LoadState.Error

        errorState?.let {
            Toast.makeText(requireContext(),"${it.error}",Toast.LENGTH_LONG).show()
        }

    }

    private fun topRatedTVWithPaginationDirectlyFromNetwork(){
        //top Rated TV with pagination from network directly

        binding.apply {
            topRatedTVRecyclerView.adapter = recyclerAdapterTV
            topRatedTVRecyclerView.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL, true
            )

            lifecycleScope.launchWhenCreated {
                retrofitViewModel.TopRatedTVList.collect {
                    recyclerAdapterTV.submitData(it)
                }
            }

        }
    }
    private fun onTheAirTVWithPaginationDirectlyFromNetwork(){

        //on the air TV with pagination from network directly

        binding.apply {
            onTheAirTVRecyclerView.adapter = recyclerAdapter2TV
            onTheAirTVRecyclerView.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL, true
            )

            lifecycleScope.launchWhenCreated {
                retrofitViewModel.OnTheAirTVList.collect {
                    recyclerAdapter2TV.submitData(it)
                }
            }

        }
    }
    private fun airingTodayTVWithPaginationDirectlyFromNetwork(){

        //Airing Today with pagination from network directly

        binding.apply {
            airingTodayTVRecyclerView.adapter = recyclerAdapter3TV
            airingTodayTVRecyclerView.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL, true
            )


            lifecycleScope.launchWhenCreated {
                retrofitViewModel.AiringTodayTVList.collect {
                    recyclerAdapter3TV.submitData(it)
                }
            }
        }

    }
}


