package com.example.movieapp.data.repository

import com.example.movieapp.data.model.MovieData
import com.example.movieapp.data.model.MovieResponse
import com.example.movieapp.data.remote_service.MovieApiService
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail

import com.example.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieService: MovieApiService) : MovieRepository {
    override suspend fun getPopularMovies(): List<Movie> {
        val response: MovieResponse = movieService.getPopularMovies()
        return response.results.map {
            it.toDomain();
        }
    }

    override suspend fun getMovieDetails(): MovieDetail {
        TODO("Not yet implemented")
    }

    private fun MovieData.toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            backdropPath = backdropPath,
            mediaType = mediaType,
            adult = adult,
            originalLanguage = originalLanguage,
            genreIds = genreIds,
            popularity = popularity,
            releaseDate = releaseDate,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}