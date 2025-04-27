package com.example.movieapp.data.remote_service


import com.example.movieapp.data.model.MovieDetailsData
import com.example.movieapp.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @Headers(
        value = [
            "accept: application/json"
        ]
    )
    @GET("trending/movie/day")
    suspend fun getPopularMovies(@Query("language") language: String = "en-US"): Response<MovieResponse>


    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetailsData>
}