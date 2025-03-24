package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor (private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): MovieDetail {
        return movieRepository.getMovieDetails(
            movieId = 426063,
            language = "en-US"
        );
    }
}