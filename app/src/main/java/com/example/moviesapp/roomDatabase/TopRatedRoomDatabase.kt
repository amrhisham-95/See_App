package com.example.moviesapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.moviesapp.models.DetailsOfTopRated


@Database(entities = [DetailsOfTopRated::class], version = 1, exportSchema = false)
abstract class TopRatedRoomDatabase : androidx.room.RoomDatabase() {


    abstract fun daoDatabase(): DaoTopRated

    companion object {
        @Volatile
        private var INSTANCE: TopRatedRoomDatabase? = null
        fun getDatabase(context: Context): TopRatedRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TopRatedRoomDatabase::class.java,
                    "topRated_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}