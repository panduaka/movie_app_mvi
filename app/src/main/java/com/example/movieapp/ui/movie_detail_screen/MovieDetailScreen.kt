package com.example.movieapp.ui.movie_detail_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MovieDetailScreen(movieId: String?) {
    Text(
        text = "Movie Detail Screen for Movie ID: $movieId",
    )
}