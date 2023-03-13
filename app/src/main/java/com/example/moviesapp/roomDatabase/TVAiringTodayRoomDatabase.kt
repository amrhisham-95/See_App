package com.example.moviesapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.moviesapp.models.DetailsOfAiringTodayTV


@Database(entities = [DetailsOfAiringTodayTV::class], version = 1, exportSchema = false)
abstract class TVAiringTodayRoomDatabase : androidx.room.RoomDatabase() {


    abstract fun daoDatabase(): DaoAiringTodayTV

    companion object {
        @Volatile
        private var INSTANCE: TVAiringTodayRoomDatabase? = null
        fun getDatabase(context: Context): TVAiringTodayRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TVAiringTodayRoomDatabase::class.java,
                    "AiringTodayTV_table"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}