package com.example.movieapp.data.repository

import com.example.movieapp.data.model.GenreData
import com.example.movieapp.data.model.MovieData
import com.example.movieapp.data.model.MovieDetailsData
import com.example.movieapp.data.model.MovieResponse
import com.example.movieapp.data.model.ProductionCompanyData
import com.example.movieapp.data.model.ProductionCountryData
import com.example.movieapp.data.model.SpokenLanguageData
import com.example.movieapp.data.remote_service.MovieApiService
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail

import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (private val movieService: MovieApiService) : MovieRepository {
    override suspend fun getPopularMovies(): List<Movie> {
        val response: MovieResponse = movieService.getPopularMovies()
        return response.results.map {
            it.toDomain();
        }
    }

    override suspend fun getMovieDetails(movieId:Int, language: String): MovieDetail {
        val response: MovieDetailsData  = movieService.getMovieDetails(
            movieId = movieId,
            language = language
        )

        return response.toMovieDetailDomain();
    }

    private  fun MovieDetailsData.toMovieDetailDomain(): MovieDetail {
        return MovieDetail(
            adult = adult,
            backdropPath = backdropPath,
            belongsToCollection = belongsToCollection,
            budget = budget,
            genres = genres.map { it.toGenreDomain() },
            homepage = homepage,
            id = id,
            imdbId = imdbId,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            productionCompanies = productionCompanies.map { it.toProductionCompanyDomain() },
            productionCountries = productionCountries.map { it.toProductionCountryDomain() },
            releaseDate = releaseDate,
            revenue = revenue,
            runtime = runtime,
            spokenLanguages = spokenLanguages.map { it.toSpokenLanguageDomain() },
            status = status,
            tagline = tagline,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }

    private fun GenreData.toGenreDomain(): MovieDetail.Genre {
        return MovieDetail.Genre(
            id = id,
            name = name
        )
    }

    private fun ProductionCompanyData.toProductionCompanyDomain(): MovieDetail.ProductionCompany {
        return MovieDetail.ProductionCompany(
            id = id,
            logoPath = logoPath,
            name = name,
            originCountry = originCountry
        )
    }

    private fun ProductionCountryData.toProductionCountryDomain(): MovieDetail.ProductionCountry {
        return MovieDetail.ProductionCountry(
            iso31661 = iso31661,
            name = name
        )
    }

    private fun SpokenLanguageData.toSpokenLanguageDomain(): MovieDetail.SpokenLanguage {
        return MovieDetail.SpokenLanguage(
            englishName = englishName,
            iso6391 = iso6391,
            name = name
        )
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