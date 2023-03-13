package com.example.moviesapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.moviesapp.models.DetailsOfOnTheAirTV

@Database(entities = [DetailsOfOnTheAirTV::class], version = 1, exportSchema = false)
abstract class TVNowPlayingRoomDatabase : androidx.room.RoomDatabase() {


    abstract fun daoDatabase(): DaoOnTheAirTV

    companion object {
        @Volatile
        private var INSTANCE: TVNowPlayingRoomDatabase? = null
        fun getDatabase(context: Context): TVNowPlayingRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TVNowPlayingRoomDatabase::class.java,
                    "TheAirTV_table"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}