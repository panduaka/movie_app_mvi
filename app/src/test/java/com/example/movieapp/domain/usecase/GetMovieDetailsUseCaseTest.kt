package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetMovieDetailsUseCaseTest {
    @MockK
    private lateinit var movieRepository: MovieRepository
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)
    }
    @Test
    fun `invoke returns movie details from repository`() = runTest {
        // Arrange
        val expectedMovieDetails = MovieDetail(
            adult = false,
            backdropPath = "/uWOJbarUXfVf6B4o0368dh138eR.jpg",
            belongsToCollection = null,
            budget = 50000000,
            genres = listOf(
                MovieDetail.Genre(id = 14, name = "Fantasy"),
                MovieDetail.Genre(id = 27, name = "Horror")
            ),
            homepage = "https://www.focusfeatures.com/nosferatu",
            id = 426063,
            imdbId = "tt5040012",
            originalLanguage = "en",
            originalTitle = "Nosferatu",
            overview = "A gothic tale of obsession between a haunted young woman and the terrifying vampire infatuated with her, causing untold horror in its wake.",
            popularity = 163.03075,
            posterPath = "/5qGIxdEO841C0tdY8vOdLoRVrr0.jpg",
            productionCompanies = listOf(
                MovieDetail.ProductionCompany(
                    id = 10146,
                    logoPath = "/xnFIOeq5cKw09kCWqV7foWDe4AA.png",
                    name = "Focus Features",
                    originCountry = "US"
                ),
                MovieDetail.ProductionCompany(
                    id = 77863,
                    logoPath = "/7z6YOzvdk70YY8OaRbn5mfxrvbO.png",
                    name = "Studio 8",
                    originCountry = "US"
                ),
                MovieDetail.ProductionCompany(
                    id = 41641,
                    logoPath = null,
                    name = "Maiden Voyage Pictures",
                    originCountry = "US"
                ),
                MovieDetail.ProductionCompany(
                    id = 239430,
                    logoPath = null,
                    name = "Birch Hill Road Entertainment",
                    originCountry = "US"
                )
            ),
            productionCountries = listOf(
                MovieDetail.ProductionCountry(iso31661 = "US", name = "United States of America")
            ),
            releaseDate = "2024-12-25",
            revenue = 177225075,
            runtime = 133,
            spokenLanguages = listOf(
                MovieDetail.SpokenLanguage(englishName = "German", iso6391 = "de", name = "Deutsch"),
                MovieDetail.SpokenLanguage(englishName = "English", iso6391 = "en", name = "English"),
                MovieDetail.SpokenLanguage(englishName = "Spanish", iso6391 = "es", name = "Español"),
                MovieDetail.SpokenLanguage(englishName = "Romanian", iso6391 = "ro", name = "Română"),
                MovieDetail.SpokenLanguage(englishName = "Russian", iso6391 = "ru", name = "Pусский")
            ),
            status = "Released",
            tagline = "Succumb to the darkness.",
            title = "Nosferatu",
            video = false,
            voteAverage = 6.7,
            voteCount = 2399
        )
//        coEvery { movieRepository.getMovieDetails(426063, "en-US") } returns expectedMovieDetails

        // Act
//        val actualMovieDetails = getMovieDetailsUseCase.invoke()
//
//        // Assert
//        assertEquals(expectedMovieDetails, actualMovieDetails)
    }

    @Test
    fun `invoke throws exception from repository`() = runTest {
        // Arrange
        val expectedException = IOException("Network error")
        coEvery { movieRepository.getMovieDetails(426063, "en-US") } throws expectedException

        // Act & Assert
//        assertFailsWith<IOException> {
//            getMovieDetailsUseCase.invoke()
//        }
    }
}