package com.example.moviesapp.repository

import com.example.moviesapp.models.*
import com.example.moviesapp.roomDatabase.*
import javax.inject.Inject

class RoomDatabaseRepository  @Inject constructor(
    private val daoTopRated: DaoTopRated,
    private val daoNowPlaying: DaoNowPlaying,
    private val daoUpComing: DaoUpComing,
    private val daoSearching: DaoSearching,
    private val daoTopRatedTV: DaoTopRatedTV,
    private val daoOnTheAirTV: DaoOnTheAirTV,
    private val AiringTodayTV: DaoAiringTodayTV,
    private val daoSearchingTV: DaoSearchingTV
) {

    suspend fun addTopRatedDataRepo(data: List<DetailsOfTopRated>) {
        daoTopRated.addData(data)
    }
    //suspend fun To delete all data :
    suspend fun deleteAllTopRatedDetails() {
        daoTopRated.deleteAllDetails()
    }
    //update data
    suspend fun updateAllTopRatedData(data: List<DetailsOfTopRated>) {
        daoTopRated.updateAllData(data)
    }


    suspend fun addNowPlayingDataRepo(data: List<DetailsOfNowPlaying>) {
        daoNowPlaying.addData(data)
    }
    //suspend fun To delete all data :
    suspend fun deleteAllNowPlayingDetails() {
        daoNowPlaying.deleteAllDetails()
    }
    //update data
    suspend fun updateAllNowPlayingData(data: List<DetailsOfNowPlaying>) {
        daoNowPlaying.updateAllData(data)
    }



    suspend fun addUpComingDataRepo(data: List<DetailsOfUpComing>) {
        daoUpComing.addData(data)
    }
    //suspend fun To delete all data :
    suspend fun deleteAllUpComingDetails() {
        daoUpComing.deleteAllDetails()
    }
    //update data
    suspend fun updateAllUpComingData(data: List<DetailsOfUpComing>) {
        daoUpComing.updateAllData(data)
    }



    suspend fun addSearchingDataRepo(data: List<DetailsOfSearching>) {
        daoSearching.addData(data)
    }
    //suspend fun To delete all data :
    suspend fun deleteAllSearchingDetails() {
        daoSearching.deleteAllDetails()
    }
    //update data
    suspend fun updateAllSearchingData(data: List<DetailsOfSearching>) {
        daoSearching.updateAllData(data)
    }

    /********************************************************************************************************************/

    suspend fun addTVTopRatedDataRepo(data: List<DetailsOfTopRatedTV>) {
        daoTopRatedTV.addData(data)
    }
    //suspend fun To delete all data :
    suspend fun deleteAllTVTopRatedDetails() {
        daoTopRatedTV.deleteAllDetails()
    }
    //update data
    suspend fun updateAllTVTopRatedData(data: List<DetailsOfTopRatedTV>) {
        daoTopRatedTV.updateAllData(data)
    }


    suspend fun addOnTheAirTVDataRepo(data: List<DetailsOfOnTheAirTV>) {
        daoOnTheAirTV.addData(data)
    }
    //suspend fun To delete all data :
    suspend fun deleteAllOnTheAirTVDetails() {
        daoOnTheAirTV.deleteAllDetails()
    }
    //update data
    suspend fun updateAllOnTheAirTVData(data: List<DetailsOfOnTheAirTV>) {
        daoOnTheAirTV.updateAllData(data)
    }



    suspend fun addAiringTodayTVDataRepo(data: List<DetailsOfAiringTodayTV>) {
        AiringTodayTV.addData(data)
    }
    //suspend fun To delete all data :
    suspend fun deleteAllAiringTodayTVDetails() {
        AiringTodayTV.deleteAllDetails()
    }
    //update data
    suspend fun updateAllAiringTodayTVData(data: List<DetailsOfAiringTodayTV>) {
        AiringTodayTV.updateAllData(data)
    }



    suspend fun addSearchingTVDataRepo(data: List<DetailsOfSearchTV>) {
        daoSearchingTV.addData(data)
    }
    //suspend fun To delete all data :
    suspend fun deleteAllSearchingTVDetails() {
        daoSearchingTV.deleteAllDetails()
    }
    //update data
    suspend fun updateAllSearchingTVData(data: List<DetailsOfSearchTV>) {
        daoSearchingTV.updateAllData(data)
    }


}