package com.example.movieapp.data.repository

import com.example.movieapp.data.model.GenreData
import com.example.movieapp.data.model.MovieData
import com.example.movieapp.data.model.MovieDetailsData
import com.example.movieapp.data.model.MovieResponse
import com.example.movieapp.data.model.ProductionCompanyData
import com.example.movieapp.data.model.ProductionCountryData
import com.example.movieapp.data.model.SpokenLanguageData
import com.example.movieapp.data.remote_service.MovieApiService
import com.example.movieapp.data.remote_service.NetworkResult
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertFailsWith
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MovieRepositoryTest {

    @MockK
    private lateinit var movieService: MovieApiService

    private lateinit var movieRepository: MovieRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieRepository = MovieRepositoryImpl(movieService)
    }

    @Test
    fun `getPopularMovies returns list of movies on success`() = runTest {
        // Arrange
        val expectedMovies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                originalTitle = "Original Movie 1",
                overview = "Overview 1",
                posterPath = "/poster1.jpg",
                backdropPath = "/backdrop1.jpg",
                mediaType = "movie",
                adult = false,
                originalLanguage = "en",
                genreIds = listOf(1, 2),
                popularity = 10.0,
                releaseDate = "2023-01-01",
                video = false,
                voteAverage = 8.0,
                voteCount = 100
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                originalTitle = "Original Movie 2",
                overview = "Overview 2",
                posterPath = "/poster2.jpg",
                backdropPath = "/backdrop2.jpg",
                mediaType = "movie",
                adult = false,
                originalLanguage = "en",
                genreIds = listOf(3, 4),
                popularity = 9.0,
                releaseDate = "2023-02-01",
                video = false,
                voteAverage = 7.5,
                voteCount = 50
            )
        )
        val movieResponse = MovieResponse(
            page = 1,
            results = listOf(
                MovieData(
                    id = 1,
                    title = "Movie 1",
                    originalTitle = "Original Movie 1",
                    overview = "Overview 1",
                    posterPath = "/poster1.jpg",
                    backdropPath = "/backdrop1.jpg",
                    mediaType = "movie",
                    adult = false,
                    originalLanguage = "en",
                    genreIds = listOf(1, 2),
                    popularity = 10.0,
                    releaseDate = "2023-01-01",
                    video = false,
                    voteAverage = 8.0,
                    voteCount = 100
                ),
                MovieData(
                    id = 2,
                    title = "Movie 2",
                    originalTitle = "Original Movie 2",
                    overview = "Overview 2",
                    posterPath = "/poster2.jpg",
                    backdropPath = "/backdrop2.jpg",
                    mediaType = "movie",
                    adult = false,
                    originalLanguage = "en",
                    genreIds = listOf(3, 4),
                    popularity = 9.0,
                    releaseDate = "2023-02-01",
                    video = false,
                    voteAverage = 7.5,
                    voteCount = 50
                )
            ),
            totalPages = 1,
            totalResults = 2
        )
        val response = Response.success(movieResponse)

        coEvery { movieService.getPopularMovies() } returns response

        // Act
        val actualMovies: NetworkResult<List<Movie>> = movieRepository.getPopularMovies()

        // Assert
        assert(actualMovies is NetworkResult.Success)
        assertEquals(expectedMovies, (actualMovies as NetworkResult.Success).data)
    }

    @Test
    fun `getPopularMovies returns error message on failure`() = runTest {
        // Arrange
        coEvery { movieService.getPopularMovies() } returns Response.error(
            400,
            "Movie API Error".toResponseBody(null)
        )
        // Act
        val actualMovies = movieRepository.getPopularMovies()

        // Assert
        assert(actualMovies is NetworkResult.Error)
        assertEquals("Error: 400", (actualMovies as NetworkResult.Error).message)
    }

    @Test
    fun `getPopularMovies throws IOException on network error`() = runTest {
        // Arrange
        coEvery { movieService.getPopularMovies() } throws RuntimeException(IOException())

        // Act & Assert
        assertFailsWith<RuntimeException> {
            movieRepository.getPopularMovies()
        }.also {
            assert(it.cause is IOException)
        }
    }

//    @Test
//    fun `getMovieDetails returns movie details on success`() = runTest {
//        // Arrange
//        val responseMovieDetails = MovieDetailsData(
//            adult = false,
//            backdropPath = "/uWOJbarUXfVf6B4o0368dh138eR.jpg",
//            belongsToCollection = null,
//            budget = 50000000,
//            genres = listOf(
//                GenreData(id = 14, name = "Fantasy"),
//                GenreData(id = 27, name = "Horror")
//            ),
//            homepage = "https://www.focusfeatures.com/nosferatu",
//            id = 426063,
//            imdbId = "tt5040012",
//            originalLanguage = "en",
//            originalTitle = "Nosferatu",
//            overview = "A gothic tale of obsession between a haunted young woman and the terrifying vampire infatuated with her, causing untold horror in its wake.",
//            popularity = 163.03075,
//            posterPath = "/5qGIxdEO841C0tdY8vOdLoRVrr0.jpg",
//            productionCompanies = listOf(
//                ProductionCompanyData(
//                    id = 10146,
//                    logoPath = "/xnFIOeq5cKw09kCWqV7foWDe4AA.png",
//                    name = "Focus Features",
//                    originCountry = "US"
//                ),
//                ProductionCompanyData(
//                    id = 77863,
//                    logoPath = "/7z6YOzvdk70YY8OaRbn5mfxrvbO.png",
//                    name = "Studio 8",
//                    originCountry = "US"
//                ),
//                ProductionCompanyData(
//                    id = 41641,
//                    logoPath = null,
//                    name = "Maiden Voyage Pictures",
//                    originCountry = "US"
//                ),
//                ProductionCompanyData(
//                    id = 239430,
//                    logoPath = null,
//                    name = "Birch Hill Road Entertainment",
//                    originCountry = "US"
//                )
//            ),
//            productionCountries = listOf(
//                ProductionCountryData(iso31661 = "US", name = "United States of America")
//            ),
//            releaseDate = "2024-12-25",
//            revenue = 177225075,
//            runtime = 133,
//            spokenLanguages = listOf(
//                SpokenLanguageData(englishName = "German", iso6391 = "de", name = "Deutsch"),
//                SpokenLanguageData(englishName = "English", iso6391 = "en", name = "English"),
//                SpokenLanguageData(englishName = "Spanish", iso6391 = "es", name = "Español"),
//                SpokenLanguageData(englishName = "Romanian", iso6391 = "ro", name = "Română"),
//                SpokenLanguageData(englishName = "Russian", iso6391 = "ru", name = "Pусский")
//            ),
//            status = "Released",
//            tagline = "Succumb to the darkness.",
//            title = "Nosferatu",
//            video = false,
//            voteAverage = 6.7,
//            voteCount = 2399
//        )
//
//        val expectedMovieDetails = MovieDetail(
//            adult = false,
//            backdropPath = "/uWOJbarUXfVf6B4o0368dh138eR.jpg",
//            belongsToCollection = null,
//            budget = 50000000,
//            genres = listOf(
//                MovieDetail.Genre(id = 14, name = "Fantasy"),
//                MovieDetail.Genre(id = 27, name = "Horror")
//            ),
//            homepage = "https://www.focusfeatures.com/nosferatu",
//            id = 426063,
//            imdbId = "tt5040012",
//            originalLanguage = "en",
//            originalTitle = "Nosferatu",
//            overview = "A gothic tale of obsession between a haunted young woman and the terrifying vampire infatuated with her, causing untold horror in its wake.",
//            popularity = 163.03075,
//            posterPath = "/5qGIxdEO841C0tdY8vOdLoRVrr0.jpg",
//            productionCompanies = listOf(
//                MovieDetail.ProductionCompany(
//                    id = 10146,
//                    logoPath = "/xnFIOeq5cKw09kCWqV7foWDe4AA.png",
//                    name = "Focus Features",
//                    originCountry = "US"
//                ),
//                MovieDetail.ProductionCompany(
//                    id = 77863,
//                    logoPath = "/7z6YOzvdk70YY8OaRbn5mfxrvbO.png",
//                    name = "Studio 8",
//                    originCountry = "US"
//                ),
//                MovieDetail.ProductionCompany(
//                    id = 41641,
//                    logoPath = null,
//                    name = "Maiden Voyage Pictures",
//                    originCountry = "US"
//                ),
//                MovieDetail.ProductionCompany(
//                    id = 239430,
//                    logoPath = null,
//                    name = "Birch Hill Road Entertainment",
//                    originCountry = "US"
//                )
//            ),
//            productionCountries = listOf(
//                MovieDetail.ProductionCountry(iso31661 = "US", name = "United States of America")
//            ),
//            releaseDate = "2024-12-25",
//            revenue = 177225075,
//            runtime = 133,
//            spokenLanguages = listOf(
//                MovieDetail.SpokenLanguage(englishName = "German", iso6391 = "de", name = "Deutsch"),
//                MovieDetail.SpokenLanguage(englishName = "English", iso6391 = "en", name = "English"),
//                MovieDetail.SpokenLanguage(englishName = "Spanish", iso6391 = "es", name = "Español"),
//                MovieDetail.SpokenLanguage(englishName = "Romanian", iso6391 = "ro", name = "Română"),
//                MovieDetail.SpokenLanguage(englishName = "Russian", iso6391 = "ru", name = "Pусский")
//            ),
//            status = "Released",
//            tagline = "Succumb to the darkness.",
//            title = "Nosferatu",
//            video = false,
//            voteAverage = 6.7,
//            voteCount = 2399
//        )
//
//        coEvery { movieService.getMovieDetails(426063, "en-US") } returns responseMovieDetails
//
//        // Act
//        val actualMovieDetails = movieRepository.getMovieDetails(movieId = 426063, language = "en-US")
//
//        // Assert
//        assertEquals(expectedMovieDetails, actualMovieDetails)
//    }

//    @Test
//    fun `getMovieDetails throws IOException on network error`() = runTest {
//        // Arrange
//        coEvery { movieService.getMovieDetails(movieId = 426063) } throws RuntimeException(IOException())
//
//        // Act & Assert
//        assertFailsWith<RuntimeException> {
//            movieRepository.getMovieDetails(movieId = 426063, language = "en-US")
//        }.also {
//            assert(it.cause is IOException)
//        }
//    }
}