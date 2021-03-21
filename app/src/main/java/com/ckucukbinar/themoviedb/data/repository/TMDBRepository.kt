package com.ckucukbinar.themoviedb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ckucukbinar.themoviedb.data.model.PopularTvShowPagingSource
import com.ckucukbinar.themoviedb.data.service.TMDBService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TMDBRepository @Inject constructor(private val tmdbService: TMDBService) {

    fun getPopularTvShowList() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {PopularTvShowPagingSource(tmdbService)}
        ).liveData
}