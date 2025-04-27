package com.example.movieapp.ui.movie_list_screen

import com.example.movieapp.domain.model.Movie

data class MovieListState(
    val isLoading: IsLoading = IsLoading(false),
    val movies: List<Movie> = emptyList(),
    val error: Error = Error(""),
    val endReached: EndReach = EndReach(false),
    val page: Page = Page(0),
    val goToMovieDetails: MovieDetail? = null
)

@JvmInline value class Page(
    val page: Int
)

@JvmInline value class Error(
    val error: String
)

@JvmInline value class IsLoading(
    val isLoading: Boolean
)

@JvmInline value class EndReach(
    val endReached: Boolean
)

@JvmInline value class MovieDetail(
    val movieId: Int
)