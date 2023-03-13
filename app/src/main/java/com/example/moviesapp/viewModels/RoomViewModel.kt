package com.example.moviesapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.models.*
import com.example.moviesapp.repository.RoomDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomDatabaseRepository: RoomDatabaseRepository,
    val liveDataTopRated: LiveData<List<DetailsOfTopRated>>,
    val liveDataNowPlaying: LiveData<List<DetailsOfNowPlaying>>,
    val liveDataUpComing: LiveData<List<DetailsOfUpComing>>,
    val liveDataSearching: LiveData<List<DetailsOfSearching>>,
    val liveDataTopRatedTV: LiveData<List<DetailsOfTopRatedTV>>,
    val liveDataOnTheAirTV: LiveData<List<DetailsOfOnTheAirTV>>,
    val liveDataAiringTodayTV: LiveData<List<DetailsOfAiringTodayTV>>,
    val liveDataSearchingTV: LiveData<List<DetailsOfSearchTV>>,
    application: Application
) : AndroidViewModel(application) {


    fun addDataTopRatedViewModel(data:List<DetailsOfTopRated>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.addTopRatedDataRepo(data)
        }
    }
    //Fun to delete all data:
    fun deleteAllDetailsTopRatedViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.deleteAllTopRatedDetails()
        }
    }
    //to update data
    fun updateDataTopRatedViewModel(data:List<DetailsOfTopRated>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.updateAllTopRatedData(data)
        }
    }

    fun addDataNowPlayingViewModel(data:List<DetailsOfNowPlaying>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.addNowPlayingDataRepo(data)
        }
    }
    //Fun to delete all data:
    fun deleteAllDetailsNowPlayingViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.deleteAllNowPlayingDetails()
        }
    }
    //to update data
    fun updateDataNowPlayingViewModel(data:List<DetailsOfNowPlaying>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.updateAllNowPlayingData(data)
        }
    }



    fun addDataUpComingViewModel(data:List<DetailsOfUpComing>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.addUpComingDataRepo(data)
        }
    }
    //Fun to delete all data:
    fun deleteAllDetailsUpComingViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.deleteAllUpComingDetails()
        }
    }
    //to update data
    fun updateDataUpComingViewModel(data:List<DetailsOfUpComing>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.updateAllUpComingData(data)
        }
    }


    fun addDataSearchingViewModel(data:List<DetailsOfSearching>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.addSearchingDataRepo(data)
        }
    }
    //Fun to delete all data:
    fun deleteAllDetailsSearchingViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.deleteAllSearchingDetails()
        }
    }
    //to update data
    fun updateDataSearchingViewModel(data:List<DetailsOfSearching>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.updateAllSearchingData(data)
        }
    }


    /**********************************************************************************************************************/


    fun addDataTVTopRatedViewModel(data:List<DetailsOfTopRatedTV>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.addTVTopRatedDataRepo(data)
        }
    }
    //Fun to delete all data:
    fun deleteAllDetailsTVTopRatedViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.deleteAllTVTopRatedDetails()
        }
    }
    //to update data
    fun updateDataTVTopRatedViewModel(data:List<DetailsOfTopRatedTV>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.updateAllTVTopRatedData(data)
        }
    }

    fun addDataOnTheAirTVViewModel(data:List<DetailsOfOnTheAirTV>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.addOnTheAirTVDataRepo(data)
        }
    }
    //Fun to delete all data:
    fun deleteAllDetailsOnTheAirTVViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.deleteAllOnTheAirTVDetails()
        }
    }
    //to update data
    fun updateDataOnTheAirTVViewModel(data:List<DetailsOfOnTheAirTV>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.updateAllOnTheAirTVData(data)
        }
    }



    fun addDataAiringTodayTVViewModel(data:List<DetailsOfAiringTodayTV>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.addAiringTodayTVDataRepo(data)
        }
    }
    //Fun to delete all data:
    fun deleteAllDetailsAiringTodayTVViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.deleteAllAiringTodayTVDetails()
        }
    }
    //to update data
    fun updateDataAiringTodayTVViewModel(data:List<DetailsOfAiringTodayTV>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.updateAllAiringTodayTVData(data)
        }
    }


    fun addDataSearchingTVViewModel(data:List<DetailsOfSearchTV>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.addSearchingTVDataRepo(data)
        }
    }
    //Fun to delete all data:
    fun deleteAllDetailsSearchingTVViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.deleteAllSearchingTVDetails()
        }
    }
    //to update data
    fun updateDataSearchingTVViewModel(data:List<DetailsOfSearchTV>){
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabaseRepository.updateAllSearchingTVData(data)
        }
    }

}