package com.example.movieapp.ui.movie_list_screen

sealed class MovieListEvent {
    data class NavigateToDetail(val movieId: Int) : MovieListEvent()
}