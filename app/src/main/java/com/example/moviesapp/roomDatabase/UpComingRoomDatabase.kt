package com.example.moviesapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.moviesapp.models.DetailsOfTopRated
import com.example.moviesapp.models.DetailsOfUpComing


@Database(entities = [DetailsOfUpComing::class], version = 1, exportSchema = false)
abstract class UpComingRoomDatabase : androidx.room.RoomDatabase() {


    abstract fun daoDatabase(): DaoUpComing

    companion object {
        @Volatile
        private var INSTANCE: UpComingRoomDatabase? = null
        fun getDatabase(context: Context): UpComingRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UpComingRoomDatabase::class.java,
                    "upComing_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }
}