package com.example.moviesapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.moviesapp.models.DetailsOfSearchTV


@Database(entities = [DetailsOfSearchTV::class], version = 1, exportSchema = false)
abstract class TVSearchingRoomDatabase : androidx.room.RoomDatabase() {


    abstract fun daoDatabase(): DaoSearchingTV

    companion object {
        @Volatile
        private var INSTANCE: TVSearchingRoomDatabase? = null
        fun getDatabase(context: Context): TVSearchingRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TVSearchingRoomDatabase::class.java,
                    "searchTV_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}