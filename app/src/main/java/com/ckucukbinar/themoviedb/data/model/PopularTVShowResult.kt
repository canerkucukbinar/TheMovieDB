package com.ckucukbinar.themoviedb.data.model

import com.ckucukbinar.themoviedb.model.TVShow

/**
 * TVShow Response
 * @param results Popular TVShow List
 */
data class PopularTVShowResult(
    val results : List<TVShow>?
)
