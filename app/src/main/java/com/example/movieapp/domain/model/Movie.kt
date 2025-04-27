package com.example.movieapp.domain.model

data class MovieResult(
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null,
    val status: Status = Status.LOADING
) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String?,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val mediaType: String,
    val adult: Boolean,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
