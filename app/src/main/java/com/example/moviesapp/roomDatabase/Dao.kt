package com.example.moviesapp.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moviesapp.models.*

@androidx.room.Dao
interface DaoTopRated {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: List<DetailsOfTopRated>)

    @Query("SELECT * FROM topRated_table ORDER BY id")
    fun readAllData() : LiveData<List<DetailsOfTopRated>>

    //To delete all data in data base:
    @Query("DELETE FROM topRated_table")
    suspend fun deleteAllDetails()

    //To Update database
    @Update
    suspend fun updateAllData(data: List<DetailsOfTopRated>)
}

@androidx.room.Dao
interface DaoNowPlaying {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: List<DetailsOfNowPlaying>)

    @Query("SELECT * FROM nowPlaying_table ORDER BY id")
    fun readAllData() : LiveData<List<DetailsOfNowPlaying>>

    //To delete all data in data base:
    @Query("DELETE FROM nowPlaying_table")
    suspend fun deleteAllDetails()

    //To Update database
    @Update
    suspend fun updateAllData(data: List<DetailsOfNowPlaying>)
}

@androidx.room.Dao
interface DaoUpComing {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: List<DetailsOfUpComing>)

    @Query("SELECT * FROM upComing_table ORDER BY id")
    fun readAllData() : LiveData<List<DetailsOfUpComing>>

    //To delete all data in data base:
    @Query("DELETE FROM upComing_table")
    suspend fun deleteAllDetails()

    //To Update database
    @Update
    suspend fun updateAllData(data: List<DetailsOfUpComing>)
}

@androidx.room.Dao
interface DaoSearching {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: List<DetailsOfSearching>)

    @Query("SELECT * FROM search_table ORDER BY id")
    fun readAllData() : LiveData<List<DetailsOfSearching>>

    //To delete all data in data base:
    @Query("DELETE FROM search_table")
    suspend fun deleteAllDetails()

    //To Update database
    @Update
    suspend fun updateAllData(data: List<DetailsOfSearching>)
}


/***********************************************************************************************************************/

@androidx.room.Dao
interface DaoTopRatedTV {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: List<DetailsOfTopRatedTV>)

    @Query("SELECT * FROM topRatedTV_table ORDER BY id")
    fun readAllData() : LiveData<List<DetailsOfTopRatedTV>>

    //To delete all data in data base:
    @Query("DELETE FROM topRatedTV_table")
    suspend fun deleteAllDetails()

    //To Update database
    @Update
    suspend fun updateAllData(data: List<DetailsOfTopRatedTV>)
}

@androidx.room.Dao
interface DaoOnTheAirTV {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: List<DetailsOfOnTheAirTV>)

    @Query("SELECT * FROM TheAirTV_table ORDER BY id")
    fun readAllData() : LiveData<List<DetailsOfOnTheAirTV>>

    //To delete all data in data base:
    @Query("DELETE FROM TheAirTV_table")
    suspend fun deleteAllDetails()

    //To Update database
    @Update
    suspend fun updateAllData(data: List<DetailsOfOnTheAirTV>)
}

@androidx.room.Dao
interface DaoAiringTodayTV {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: List<DetailsOfAiringTodayTV>)

    @Query("SELECT * FROM AiringTodayTV_table ORDER BY id")
    fun readAllData() : LiveData<List<DetailsOfAiringTodayTV>>

    //To delete all data in data base:
    @Query("DELETE FROM AiringTodayTV_table")
    suspend fun deleteAllDetails()

    //To Update database
    @Update
    suspend fun updateAllData(data: List<DetailsOfAiringTodayTV>)
}

@androidx.room.Dao
interface DaoSearchingTV {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: List<DetailsOfSearchTV>)

    @Query("SELECT * FROM SearchTV_table ORDER BY id")
    fun readAllData() : LiveData<List<DetailsOfSearchTV>>

    //To delete all data in data base:
    @Query("DELETE FROM SearchTV_table")
    suspend fun deleteAllDetails()

    //To Update database
    @Update
    suspend fun updateAllData(data: List<DetailsOfSearchTV>)
}