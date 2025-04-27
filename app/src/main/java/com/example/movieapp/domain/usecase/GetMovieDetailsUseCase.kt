package com.example.movieapp.domain.usecase

import com.example.movieapp.data.remote_service.NetworkResult
import com.example.movieapp.domain.model.MovieDetailResult
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor (private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: Int, language: String): MovieDetailResult {
        val result = movieRepository.getMovieDetails(
            movieId = movieId,
            language = language
        );
        return when(result) {
            is NetworkResult.Success -> {
               MovieDetailResult(
                   movieDetail = result.data,
                   status = MovieDetailResult.Status.SUCCESS
               )
            }
            is NetworkResult.Error -> {
                MovieDetailResult(
                    errorMessage = result.message,
                    status = MovieDetailResult.Status.ERROR
                )
            }
            else -> {
                MovieDetailResult(
                    status = MovieDetailResult.Status.ERROR
                )
            }
        }
    }
}