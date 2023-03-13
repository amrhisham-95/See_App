package com.example.moviesapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.moviesapp.models.DetailsOfSearching
import com.example.moviesapp.models.DetailsOfTopRated


@Database(entities = [DetailsOfSearching::class], version = 1, exportSchema = false)
abstract class SearchingRoomDatabase : androidx.room.RoomDatabase() {


    abstract fun daoDatabase(): DaoSearching

    companion object {
        @Volatile
        private var INSTANCE: SearchingRoomDatabase? = null
        fun getDatabase(context: Context): SearchingRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SearchingRoomDatabase::class.java,
                    "search_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}