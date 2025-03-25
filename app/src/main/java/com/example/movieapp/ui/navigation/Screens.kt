package com.example.movieapp.ui.navigation

sealed class Screen(val route: String) {
    object MovieList: Screen("movie_list_screen")
    object MovieDetail: Screen("movie_detail_screen")
}