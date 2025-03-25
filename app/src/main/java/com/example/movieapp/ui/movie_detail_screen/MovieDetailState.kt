package com.example.movieapp.ui.movie_detail_screen

import com.example.movieapp.domain.model.MovieDetail

sealed class MovieDetailState {
    data object Loading : MovieDetailState()
    data class Success(val movieDetail: MovieDetail) : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
}