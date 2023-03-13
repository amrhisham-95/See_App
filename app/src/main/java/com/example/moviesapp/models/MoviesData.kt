package com.example.moviesapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class TopRatedData(
    var page : Int,
    var results : List<DetailsOfTopRated>,
    var total_pages : Int
)


@Entity("topRated_table")
@Parcelize
data class DetailsOfTopRated(
    var adult : Boolean,
    var backdrop_path : String?,
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    var original_language :String,
    var original_title : String,
    var overview :String,
    var poster_path : String?,
    var release_date : String,
    var title : String,
    var vote_count : Int
):Parcelable

@JsonClass(generateAdapter = true)
data class NowPlayingData(
    var page : Int,
    var results : List<DetailsOfNowPlaying>,
    var total_pages : Int
)

@Entity("nowPlaying_table")
@Parcelize
data class DetailsOfNowPlaying(
    var adult : Boolean,
    var backdrop_path : String?,
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    var original_language :String,
    var original_title : String,
    var overview :String,
    var poster_path : String?,
    var release_date : String,
    var title : String,
    var vote_count : Int
):Parcelable



@JsonClass(generateAdapter = true)
data class UpComingData(
    var page : Int,
    var results : List<DetailsOfUpComing>,
    var total_pages : Int
)

@Entity("upComing_table")
@Parcelize
data class DetailsOfUpComing(
    var adult : Boolean,
    var backdrop_path : String?,
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    var original_language :String,
    var original_title : String,
    var overview :String,
    var poster_path : String?,
    var release_date : String,
    var title : String,
    var vote_count : Int
):Parcelable


@JsonClass(generateAdapter = true)
data class SearchData(
    var page : Int,
    var results : List<DetailsOfSearching>,
    var total_pages : Int
)

@Entity("search_table")
@Parcelize
data class DetailsOfSearching(
    var adult : Boolean,
    var backdrop_path : String?,
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    var original_language :String,
    var original_title : String,
    var overview :String,
    var poster_path : String?,
    var release_date : String,
    var title : String,
    var vote_count : Int
):Parcelable


