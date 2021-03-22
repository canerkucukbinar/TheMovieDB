package com.ckucukbinar.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * TVShow Response
 * @param id ID
 * @param name TV Show Name
 * @param voteAverage Average of the votes cast.
 * @param imagePath TV Show Poster Path
 * @param overview Overview
 * @param first_air_date Date
 * @param origin_country Country
 * @param original_name Original Name
 * @param vote_count Vote Count
 */

@Parcelize
data class TVShow(
    @SerializedName("id")  val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("poster_path") val imagePath: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("origin_country") val originCountry: List<String>?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("vote_count") val voteCount: Int?,
) : Parcelable
