package com.example.moviesapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.moviesapp.models.DetailsOfNowPlaying


@Database(entities = [DetailsOfNowPlaying::class], version = 1, exportSchema = false)
abstract class NowPlayingRoomDatabase : androidx.room.RoomDatabase() {


    abstract fun daoDatabase(): DaoNowPlaying

    companion object {
        @Volatile
        private var INSTANCE: NowPlayingRoomDatabase? = null
        fun getDatabase(context: Context): NowPlayingRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NowPlayingRoomDatabase::class.java,
                    "nowPlaying_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}