package com.ckucukbinar.themoviedb.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ckucukbinar.themoviedb.data.service.TMDBService
import com.ckucukbinar.themoviedb.model.TVShow
import retrofit2.HttpException
import java.io.IOException

private val FIRST_PAGE_INDEX = 1

class PopularTvShowPagingSource(
    private val tmdbService: TMDBService
) : PagingSource <Int, TVShow>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShow> {
        return try {
            val positon = params.key ?: FIRST_PAGE_INDEX
            val response = tmdbService.getPopularTVShowList(positon)
            val popularTvShowList = response.results
            popularTvShowList?.let { tvShowList ->
                LoadResult.Page(
                    data = tvShowList,
                    prevKey = if(positon == FIRST_PAGE_INDEX) null else positon-1,
                    nextKey = if(tvShowList.isEmpty()) null else positon+1
                )
            } ?:run {
                LoadResult.Error(throw NullPointerException())
            }
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TVShow>): Int? {
        TODO("Not yet implemented")
    }
}