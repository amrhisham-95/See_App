package com.example.moviesapp.models

import com.example.moviesapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.SEARCH_PATH)
    suspend fun getDetailsOfSpecificMovieFromService(
        @Query("api_key") api_key : String,
        @Query("query") query : String,
    ) : Response<SearchData>

    @GET(Constants.NOW_PLAYING_PATH)
    suspend fun getDetailsOfNowPlayingMoviesFromService(
        @Query("api_key") api_key : String,
        @Query("page") page : Int
    ) : Response<NowPlayingData>

    @GET(Constants.TOP_RATED_PATH)
    suspend fun getDetailsOfTopRatedMoviesFromService(
        @Query("api_key") api_key : String,
        @Query("page") page : Int
    ) : Response<TopRatedData>


    @GET(Constants.UP_COMING_PATH)
    suspend fun getDetailsOfUpComingMoviesFromService(
        @Query("api_key") api_key : String,
        @Query("page") page : Int
    ) : Response<UpComingData>


    @GET(Constants.SEARCH_PATH_TV)
    suspend fun getDetailsOfSpecificTVFromService(
        @Query("api_key") api_key : String,
        @Query("query") query : String
    ) : Response<TVDataSearch>

    @GET(Constants.On_The_Air_PATH_TV)
    suspend fun getDetailsOfOnTheAirTVFromService(
        @Query("api_key") api_key : String,
        @Query("page") page : Int
    ) : Response<TVDataOnTheAir>

    @GET(Constants.TOP_RATED_PATH_TV)
    suspend fun getDetailsOfTopRatedTVFromService(
        @Query("api_key") api_key : String,
        @Query("page") page : Int
    ) : Response<TVDataTopRated>


    @GET(Constants.Airing_Today_PATH_TV)
    suspend fun getDetailsOfAiringTodayTVFromService(
        @Query("api_key") api_key : String,
        @Query("page") page : Int
    ) : Response<TVDataAiringToday>

}