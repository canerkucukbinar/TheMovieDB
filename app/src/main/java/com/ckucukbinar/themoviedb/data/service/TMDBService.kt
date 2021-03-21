package com.ckucukbinar.themoviedb.data.service

import com.ckucukbinar.themoviedb.model.PopularTVShowResult
import com.ckucukbinar.themoviedb.model.TVShow
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDBService {

    object Endpoint {
        const val apiKey = "11500d5e3e3beaa5e3153315721f5e8d"
        const val baseURL = "https://api.themoviedb.org/3/"
        const val imageURLPrefix = "https://image.tmdb.org/t/p/w500"


        object TV {
            const val maintPath = "$baseURL/tv"
            const val popular = "$maintPath/popular"
            const val detail = "$maintPath/{tv_id}"
        }
    }

    @GET("${Endpoint.TV.detail}?api_key=${Endpoint.apiKey}")
    fun getTVShowDetail(
        @Query("language") language: String?
    ) {
    }

    @GET("${Endpoint.TV.popular}?api_key=${Endpoint.apiKey}")
    suspend fun getPopularTVShowList(
        @Query("page") page: Int?,
        @Query("language") language: String? = null
    ) : PopularTVShowResult
}
