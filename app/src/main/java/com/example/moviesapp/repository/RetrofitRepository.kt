package com.example.moviesapp.repository

import androidx.paging.*
import com.example.moviesapp.models.*
import com.example.moviesapp.paging.*
import com.example.moviesapp.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val PAGE_SIZE = 30
class RetrofitRepository @Inject constructor(
    val apiService: ApiService
) {

    //for getting the detailsOfTopRated by (pagination)
    @ExperimentalPagingApi
    fun getRepositoryDetailsOfTopRatedFromServiceByPagination(): Flow<PagingData<DetailsOfTopRated>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopRatedMoviesPagingSource(apiService) }
        ).flow
    }

    //for getting the detailsOfNowPlaying by (pagination)
    @ExperimentalPagingApi
    fun getRepositoryDetailsOfNowPlayingFromServiceByPagination(): Flow<PagingData<DetailsOfNowPlaying>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NowPlayingMoviesPagingSource(apiService) }
        ).flow
    }


    //for getting the detailsOfUpComing by (pagination)
    @ExperimentalPagingApi
    fun getRepositoryDetailsOfUpComingFromServiceByPagination(): Flow<PagingData<DetailsOfUpComing>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UpComingMoviesPagingSource(apiService) }
        ).flow
    }


    //for getting the DetailsOfSpecific
    suspend fun getRepositoryDetailsOfSpecificFromService(
        api_key: String,
        query: String
    ): Flow<State<SearchData?>> {
        return flow {
            emit(State.Loading)
            val result =   apiService.getDetailsOfSpecificMovieFromService(
                api_key, query
            )

            if (result.isSuccessful) {
                emit(State.Success(result.body()))
            } else {
                emit(State.Error(result.message()))
            }
        }
    }


    //for getting the DetailsOfSpecificTV
    suspend fun getRepositoryDetailsOfSpecificTVFromService(
        api_key: String,
        query: String
    ): Flow<State<TVDataSearch?>> {
        return flow {
            emit(State.Loading)
            val result =   apiService.getDetailsOfSpecificTVFromService(
                api_key, query
            )

            if (result.isSuccessful) {
                emit(State.Success(result.body()))
            } else {
                emit(State.Error(result.message()))
            }
        }
    }




    //for getting the detailsOfTopRatedTV by (pagination)
    @ExperimentalPagingApi
    fun getRepositoryDetailsOfTopRatedTVFromServiceByPagination(): Flow<PagingData<DetailsOfTopRatedTV>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopRatedTVPagingSource(apiService) }
        ).flow
    }


    //for getting the OnTheAirDetailsOfTV by (pagination)
    @ExperimentalPagingApi
    fun getRepositoryOnTheAirDetailsOfTVFromServiceByPagination(): Flow<PagingData<DetailsOfOnTheAirTV>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { OnTheAirTVPagingSource(apiService) }
        ).flow
    }


    //for getting the AiringTodayTVDetails by (pagination)
    @ExperimentalPagingApi
    fun getRepositoryAiringTodayTVDetailsOfTVFromServiceByPagination(): Flow<PagingData<DetailsOfAiringTodayTV>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AiringTodayTVPagingSource(apiService) }
        ).flow
    }

}