package com.example.moviesapp.di

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.moviesapp.models.*
import com.example.moviesapp.roomDatabase.*
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.State
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiRetrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMutableLiveDataTopRatedVariable() = MutableLiveData<State<TopRatedData>>()

    @Provides
    @Singleton
    fun provideMutableLiveDataNowPlayingVariable() = MutableLiveData<State<NowPlayingData>>()

    @Provides
    @Singleton
    fun provideMutableLiveDataUpComingVariable() = MutableLiveData<State<UpComingData>>()

    @Provides
    @Singleton
    fun provideMutableLiveDataSearchingVariable() = MutableLiveData<State<SearchData>>()

    @Provides
    @Singleton
    fun provideGetDaoTopRated(appDB: TopRatedRoomDatabase): DaoTopRated {
        return appDB.daoDatabase()
    }

    @Provides
    @Singleton
    fun provideGetDaoNowPlaying(appDB: NowPlayingRoomDatabase): DaoNowPlaying{
        return appDB.daoDatabase()
    }

    @Provides
    @Singleton
    fun provideGetDaoUpComing(appDB: UpComingRoomDatabase): DaoUpComing {
        return appDB.daoDatabase()
    }

    @Provides
    @Singleton
    fun provideGetDaoSearching(appDB: SearchingRoomDatabase): DaoSearching{
        return appDB.daoDatabase()
    }

    @Provides
    @Singleton
    fun provideRoomDatabaseTopRated(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,TopRatedRoomDatabase::class.java,"topRated_database"
    ).build()

    @Provides
    @Singleton
    fun provideRoomDatabaseNowPlaying(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,NowPlayingRoomDatabase::class.java,"nowPlaying_database"
    ).build()

    @Provides
    @Singleton
    fun provideRoomDatabaseUpComing(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,UpComingRoomDatabase::class.java,"upComing_database"
    ).build()

    @Provides
    @Singleton
    fun provideRoomDatabaseSearching(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,SearchingRoomDatabase::class.java,"search_database"
    ).build()

    @Provides
    @Singleton
    fun provideLiveDataVariableTopRated(appDB: TopRatedRoomDatabase) : LiveData<List<DetailsOfTopRated>> = appDB.daoDatabase().readAllData()

    @Provides
    @Singleton
    fun provideLiveDataVariableNowPlaying(appDB: NowPlayingRoomDatabase) : LiveData<List<DetailsOfNowPlaying>> = appDB.daoDatabase().readAllData()

    @Provides
    @Singleton
    fun provideLiveDataVariableUpComing(appDB: UpComingRoomDatabase) : LiveData<List<DetailsOfUpComing>> = appDB.daoDatabase().readAllData()

    @Provides
    @Singleton
    fun provideLiveDataVariableSearching(appDB: SearchingRoomDatabase) : LiveData<List<DetailsOfSearching>> = appDB.daoDatabase().readAllData()



    /********************************************************************************************************************/

    @Provides
    @Singleton
    fun provideMutableLiveDataTopRatedTVVariable() = MutableLiveData<State<TVDataTopRated>>()

    @Provides
    @Singleton
    fun provideMutableLiveDataOnTheAirTVVariable() = MutableLiveData<State<TVDataOnTheAir>>()

    @Provides
    @Singleton
    fun provideMutableLiveDataAiringTodayTVVariable() = MutableLiveData<State<TVDataAiringToday?>>()

    @Provides
    @Singleton
    fun provideMutableLiveDataSearchingTVVariable() = MutableLiveData<State<TVDataSearch>>()

    @Provides
    @Singleton
    fun provideGetDaoTopRatedTV(appDB: TVTopRatedRoomDatabase): DaoTopRatedTV {
        return appDB.daoDatabase()
    }

    @Provides
    @Singleton
    fun provideGetDaoOnTheAirTV(appDB: TVNowPlayingRoomDatabase): DaoOnTheAirTV{
        return appDB.daoDatabase()
    }

    @Provides
    @Singleton
    fun provideGetDaoAiringTodayTV(appDB: TVAiringTodayRoomDatabase): DaoAiringTodayTV {
        return appDB.daoDatabase()
    }

    @Provides
    @Singleton
    fun provideGetDaoSearchingTV(appDB: TVSearchingRoomDatabase): DaoSearchingTV{
        return appDB.daoDatabase()
    }

    @Provides
    @Singleton
    fun provideRoomDatabaseTopRatedTV(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,TVTopRatedRoomDatabase::class.java,"topRatedTV_table"
    ).build()

    @Provides
    @Singleton
    fun provideRoomDatabaseOnTheAirTV(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,TVNowPlayingRoomDatabase::class.java,"TheAirTV_table"
    ).build()

    @Provides
    @Singleton
    fun provideRoomDatabaseAiringTodayTV(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,TVAiringTodayRoomDatabase::class.java,"AiringTodayTV_table"
    ).build()

    @Provides
    @Singleton
    fun provideRoomDatabaseSearchingTV(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,TVSearchingRoomDatabase::class.java,"SearchTV_table"
    ).build()

    @Provides
    @Singleton
    fun provideLiveDataVariableTopRatedTV(appDB: TVTopRatedRoomDatabase) : LiveData<List<DetailsOfTopRatedTV>> = appDB.daoDatabase().readAllData()

    @Provides
    @Singleton
    fun provideLiveDataVariableOnTheAirTV(appDB: TVNowPlayingRoomDatabase) : LiveData<List<DetailsOfOnTheAirTV>> = appDB.daoDatabase().readAllData()

    @Provides
    @Singleton
    fun provideLiveDataVariableAiringTodayTV(appDB: TVAiringTodayRoomDatabase) : LiveData<List<DetailsOfAiringTodayTV>> = appDB.daoDatabase().readAllData()

    @Provides
    @Singleton
    fun provideLiveDataVariableSearchingTV(appDB: TVSearchingRoomDatabase) : LiveData<List<DetailsOfSearchTV>> = appDB.daoDatabase().readAllData()




}