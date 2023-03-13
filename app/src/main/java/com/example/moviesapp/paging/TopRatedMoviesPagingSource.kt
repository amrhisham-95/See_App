package com.example.moviesapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.models.ApiService
import com.example.moviesapp.models.DetailsOfTopRated
import com.example.moviesapp.repository.RetrofitRepository
import com.example.moviesapp.utils.Constants
import kotlinx.coroutines.delay
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX =1
class TopRatedMoviesPagingSource (private val apiService : ApiService) : PagingSource<Int, DetailsOfTopRated>(){
    override fun getRefreshKey(state: PagingState<Int, DetailsOfTopRated>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DetailsOfTopRated> {
        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getDetailsOfTopRatedMoviesFromService(Constants.API_KEY,currentPage)
            val responseData = mutableListOf<DetailsOfTopRated>()
            val data = response.body()!!.results
            responseData.addAll(data)

            delay(2000)
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