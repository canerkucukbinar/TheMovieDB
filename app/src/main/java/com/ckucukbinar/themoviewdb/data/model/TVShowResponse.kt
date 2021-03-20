package com.ckucukbinar.themoviewdb.model

import com.google.gson.annotations.SerializedName


/**
 * TVShow Response
 * @param id ID
 * @param name TV Show Name
 * @param voteAverage Average of the votes cast.
 * @param imagePath TV Show Poster Path
 */
data class TVShowResponse(
    @SerializedName("id")  val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("poster_path") val imagePath: String?
)
