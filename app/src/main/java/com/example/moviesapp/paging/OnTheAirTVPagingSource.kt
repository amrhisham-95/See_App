package com.example.moviesapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.models.ApiService
import com.example.moviesapp.models.DetailsOfNowPlaying
import com.example.moviesapp.models.DetailsOfOnTheAirTV
import com.example.moviesapp.repository.RetrofitRepository
import com.example.moviesapp.utils.Constants
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX =1

class OnTheAirTVPagingSource(private val apiService : ApiService) :
    PagingSource<Int, DetailsOfOnTheAirTV>() {
    override fun getRefreshKey(state: PagingState<Int, DetailsOfOnTheAirTV>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DetailsOfOnTheAirTV> {
        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getDetailsOfOnTheAirTVFromService(Constants.API_KEY,currentPage)
            val responseData = mutableListOf<DetailsOfOnTheAirTV>()
            val data = response.body()!!.results
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if(currentPage== STARTING_PAGE_INDEX) null else -1,
                nextKey = if(data.isEmpty()) null else {currentPage.plus(1)}
            )

        }catch (e : Exception){
            LoadResult.Error(e)
        }catch (httpE : HttpException){
            LoadResult.Error(httpE)
        }
    }


}