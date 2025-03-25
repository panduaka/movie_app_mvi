package com.example.movieapp.ui.movie_list_screen

sealed interface MovieListIntent {
    data object Initial : MovieListIntent
    data class SelectMovie(val movieId: Int) : MovieListIntent
}