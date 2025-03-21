package com.example.movieapp.domain.usecase


import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.model.Movie

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getPopularMovies()
    }
}