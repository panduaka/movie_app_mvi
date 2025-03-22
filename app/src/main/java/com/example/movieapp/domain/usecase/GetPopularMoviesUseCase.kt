package com.example.movieapp.domain.usecase


import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.model.Movie
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor (private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getPopularMovies()
    }
}