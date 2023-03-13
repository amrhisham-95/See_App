package com.example.moviesapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.moviesapp.models.*
import com.example.moviesapp.repository.RetrofitRepository
import com.example.moviesapp.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val repoRetrofitRepository: RetrofitRepository,
    val mutableLiveDataTopRated: MutableLiveData<State<TopRatedData?>>,
    val mutableLiveDataNowPlaying: MutableLiveData<State<NowPlayingData?>>,
    val mutableLiveDataUpComing: MutableLiveData<State<UpComingData?>>,
    val mutableLiveDataSearching: MutableLiveData<State<SearchData?>>,
    val mutableLiveDataTopRatedTV: MutableLiveData<State<TVDataTopRated?>>,
    val mutableLiveDataOnTheAirTV: MutableLiveData<State<TVDataOnTheAir?>>,
    val mutableLiveDataAiringTodayTV: MutableLiveData<State<TVDataAiringToday?>>,
    val mutableLiveDataSearchingTV: MutableLiveData<State<TVDataSearch?>>
) : ViewModel() {


    //for put the data directly from the service without put it into room database with pagination (top Rated movies)
    @OptIn(ExperimentalPagingApi::class)
    var movieTopRatedList =
        repoRetrofitRepository.getRepositoryDetailsOfTopRatedFromServiceByPagination().cachedIn(viewModelScope)

    //for put the data directly from the service without put it into room database with pagination (top Rated TV)
    @OptIn(ExperimentalPagingApi::class)
    var TopRatedTVList =
        repoRetrofitRepository.getRepositoryDetailsOfTopRatedTVFromServiceByPagination().cachedIn(viewModelScope)

    //for put the data directly from the service without put it into room database with pagination (now playing movies)
    @OptIn(ExperimentalPagingApi::class)
    var movieNowPlayingMoviesList =
        repoRetrofitRepository.getRepositoryDetailsOfNowPlayingFromServiceByPagination().cachedIn(viewModelScope)

    //for put the data directly from the service without put it into room database with pagination (on the air TV)
    @OptIn(ExperimentalPagingApi::class)
    var OnTheAirTVList =
        repoRetrofitRepository.getRepositoryOnTheAirDetailsOfTVFromServiceByPagination().cachedIn(viewModelScope)

    //for put the data directly from the service without put it into room database with pagination (Up Coming movies)
    @OptIn(ExperimentalPagingApi::class)
    var movieUpComingList =
        repoRetrofitRepository.getRepositoryDetailsOfUpComingFromServiceByPagination().cachedIn(viewModelScope)

    //for put the data directly from the service without put it into room database with pagination (airingToday TV movies)
    @OptIn(ExperimentalPagingApi::class)
    var AiringTodayTVList =
        repoRetrofitRepository.getRepositoryAiringTodayTVDetailsOfTVFromServiceByPagination().cachedIn(viewModelScope)




    //for put the data directly from the service without put it into room database (search movie)
    fun getRetrofitViewModelDetailsOfSpecificFromService(
        api_key: String,
        query: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repoRetrofitRepository.getRepositoryDetailsOfSpecificFromService(
                    api_key, query
                ).collect {
                    mutableLiveDataSearching.postValue(it)
                }
            }
        }
    }

    //for put the data directly from the service without put it into room database (search TV)
    fun getRetrofitViewModelDetailsOfSpecificTVFromService(
        api_key: String,
        query: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repoRetrofitRepository.getRepositoryDetailsOfSpecificTVFromService(
                    api_key, query
                ).collect {
                    mutableLiveDataSearchingTV.postValue(it)
                }
            }
        }
    }


}