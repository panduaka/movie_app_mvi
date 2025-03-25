package com.example.movieapp.ui.movie_list_screen

sealed interface MovieListIntent {
    object Initial : MovieListIntent
}