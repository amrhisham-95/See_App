package com.example.moviesapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
data class TVDataTopRated(
    var page : Int,
    var results : List<DetailsOfTopRatedTV>,
    var total_pages : Int
)

@Entity("topRatedTV_table")
@Parcelize
data class DetailsOfTopRatedTV(
    var poster_path : String?,
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    var backdrop_path : String?,
    var overview :String,
    var first_air_date: String,
    var original_language :String,
    var vote_count : Int,
    var name : String,
    var original_name : String
): Parcelable


@JsonClass(generateAdapter = true)
data class TVDataOnTheAir(
    var page : Int,
    var results : List<DetailsOfOnTheAirTV>,
    var total_pages : Int
)

@Entity("TheAirTV_table")
@Parcelize
data class DetailsOfOnTheAirTV(
    var poster_path : String?,
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    var backdrop_path : String?,
    var overview :String,
    var first_air_date: String,
    var original_language :String,
    var vote_count : Int,
    var name : String,
    var original_name : String
): Parcelable


@JsonClass(generateAdapter = true)
data class TVDataAiringToday(
    var page : Int,
    var results : List<DetailsOfAiringTodayTV>,
    var total_pages : Int
)

@Entity("AiringTodayTV_table")
@Parcelize
data class DetailsOfAiringTodayTV(
    var poster_path : String?,
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    var backdrop_path : String?,
    var overview :String,
    var first_air_date: String,
    var original_language :String,
    var vote_count : Int,
    var name : String,
    var original_name : String
): Parcelable

@JsonClass(generateAdapter = true)
data class TVDataSearch(
    var page : Int,
    var results : List<DetailsOfSearchTV>,
    var total_pages : Int
)

@Entity("SearchTV_table")
@Parcelize
data class DetailsOfSearchTV(
    var poster_path : String?,
    @PrimaryKey(autoGenerate = false)
    var id : Int,
    var backdrop_path : String?,
    var overview :String,
    var first_air_date: String,
    var original_language :String,
    var vote_count : Int,
    var name : String,
    var original_name : String
): Parcelable

