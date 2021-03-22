package com.ckucukbinar.themoviedb.data.service

import com.ckucukbinar.themoviedb.data.model.PopularTVShowResult
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDBService {

    object Endpoint {
        const val apiKey = "11500d5e3e3beaa5e3153315721f5e8d"
        const val baseURL = "https://api.themoviedb.org/3/"
        const val imageURLPrefix = "https://image.tmdb.org/t/p/w500"


        object TV {
            private const val maintPath = "tv/"
            const val popular = "${maintPath}popular"
            const val detail = "${maintPath}{tv_id}"
        }
    }

    @GET(Endpoint.TV.detail)
    fun getTVShowDetail(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) {
    }

    @GET(Endpoint.TV.popular)
    suspend fun getPopularTVShowList(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?,
        @Query("language") language: String? = null
    ) : PopularTVShowResult
}
