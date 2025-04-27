package com.example.movieapp.ui.movie_detail_screen


sealed interface MovieDetailsAction {
    data object goBack : MovieDetailsAction
}