package com.example.movieapp.ui.movie_detail_screen

import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.ui.movie_list_screen.Error
import com.example.movieapp.ui.movie_list_screen.IsLoading

data class MovieDetailState(
    val movieDetail: MovieDetail? = null,
    val isLoading: IsLoading = IsLoading(isLoading = false),
    val error: Error = Error(error = "")
)

