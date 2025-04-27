package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetPopularMoviesUseCaseTest {

//    @MockK
//    private lateinit var movieRepository: MovieRepository
//
//    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
//
//    @Before
//    fun setUp() {
//        MockKAnnotations.init(this)
//        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
//    }
//
//    @Test
//    fun `invoke returns list of movies from repository`() = runTest {
//        // Arrange
//        val expectedMovies = listOf(
//            Movie(
//                id = 1,
//                title = "Movie 1",
//                originalTitle = "Original Movie 1",
//                overview = "Overview 1",
//                posterPath = "/poster1.jpg",
//                backdropPath = "/backdrop1.jpg",
//                mediaType = "movie",
//                adult = false,
//                originalLanguage = "en",
//                genreIds = listOf(1, 2),
//                popularity = 10.0,
//                releaseDate = "2023-01-01",
//                video = false,
//                voteAverage = 8.0,
//                voteCount = 100
//            ),
//            Movie(
//                id = 2,
//                title = "Movie 2",
//                originalTitle = "Original Movie 2",
//                overview = "Overview 2",
//                posterPath = "/poster2.jpg",
//                backdropPath = "/backdrop2.jpg",
//                mediaType = "movie",
//                adult = false,
//                originalLanguage = "en",
//                genreIds = listOf(3, 4),
//                popularity = 9.0,
//                releaseDate = "2023-02-01",
//                video = false,
//                voteAverage = 7.5,
//                voteCount = 50
//            )
//        )
//        coEvery { movieRepository.getPopularMovies() } returns expectedMovies
//
//        // Act
//        val actualMovies = getPopularMoviesUseCase.invoke()
//
//        // Assert
//        assertEquals(expectedMovies, actualMovies)
//    }
//
//    @Test
//    fun `invoke throws exception from repository`() = runTest {
//        // Arrange
//        val expectedException = IOException("Network error")
//        coEvery { movieRepository.getPopularMovies() } throws expectedException
//
//        // Act & Assert
//        assertFailsWith<IOException> {
//            getPopularMoviesUseCase.invoke()
//        }
//    }
}