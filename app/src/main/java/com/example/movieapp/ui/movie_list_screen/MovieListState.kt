package com.example.movieapp.ui.movie_list_screen

import com.example.movieapp.domain.model.Movie

sealed interface MovieListState {
    object Loading : MovieListState
    data class Success(val movies: List<Movie>, val page: Int = 1, val totalPages: Int = 1) : MovieListState
    data class Error(val message: String) : MovieListState
}