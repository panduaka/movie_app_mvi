package com.example.movieapp.ui.navigation

sealed class Screen(val route: String) {
    data object MovieList: Screen("movie_list_screen")
    data object MovieDetail: Screen("movie_detail_screen")
}