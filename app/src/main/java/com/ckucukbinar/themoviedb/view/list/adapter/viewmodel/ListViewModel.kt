package com.ckucukbinar.themoviedb.view.list.adapter.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.ckucukbinar.themoviedb.data.repository.TMDBRepository

class ListViewModel @ViewModelInject constructor(private val tmdbRepository: TMDBRepository) : ViewModel() {
    val popularTVShowList = tmdbRepository.getPopularTvShowList()
}