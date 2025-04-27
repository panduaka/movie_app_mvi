package com.example.movieapp.domain.usecase


import com.example.movieapp.data.remote_service.NetworkResult
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor (private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): MovieResult {
        return when (val result = movieRepository.getPopularMovies()) {
            is NetworkResult.Loading -> {
                MovieResult(status = MovieResult.Status.LOADING)
            }
            is NetworkResult.Success -> {
                MovieResult(
                    movies = result.data ?: emptyList(),
                    status = MovieResult.Status.SUCCESS
                )
            }
            is NetworkResult.Error -> {
                MovieResult(
                    errorMessage = result.message,
                    status = MovieResult.Status.ERROR
                )
            }

        }
    }
}