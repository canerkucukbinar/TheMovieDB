package com.ckucukbinar.themoviedb.view.list.adapter.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ckucukbinar.themoviedb.data.repository.TMDBRepository

class ListViewModel @ViewModelInject constructor(private val tmdbRepository: TMDBRepository) : ViewModel() {
    val popularTVShowList = tmdbRepository.getPopularTvShowList().cachedIn(viewModelScope)
}