package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail


/**
 *  Repositories are interfaces that define how data is accessed.
 *  They abstract away the details of where the data comes from (network, database, etc.)
 */
interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getMovieDetails(): MovieDetail
}