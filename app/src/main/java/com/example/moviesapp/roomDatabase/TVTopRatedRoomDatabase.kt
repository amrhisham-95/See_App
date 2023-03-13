package com.example.moviesapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.moviesapp.models.DetailsOfTopRated
import com.example.moviesapp.models.DetailsOfTopRatedTV


@Database(entities = [DetailsOfTopRatedTV::class], version = 1, exportSchema = false)
abstract class TVTopRatedRoomDatabase : androidx.room.RoomDatabase() {


    abstract fun daoDatabase(): DaoTopRatedTV

    companion object {
        @Volatile
        private var INSTANCE: TVTopRatedRoomDatabase? = null
        fun getDatabase(context: Context): TVTopRatedRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TVTopRatedRoomDatabase::class.java,
                    "topRatedTV_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}