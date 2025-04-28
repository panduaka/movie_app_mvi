package com.example.movieapp.ui.movie_list_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.domain.usecase.GetPopularMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class MovieListViewModelTest {
    //Setup method

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieListViewModel
    private lateinit var testDispatcher: TestDispatcher

    @MockK
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieListViewModel(getPopularMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch Movies Successfully`() = runTest {
        // Given

        val movieResult = MovieResult(
            movies = listOf(
                Movie(
                    id = 1,
                    title = "Movie 1",
                    originalTitle = null,
                    overview = "Good Movie",
                    posterPath = null,
                    backdropPath = null,
                    mediaType = "VHS",
                    adult = true,
                    originalLanguage = "English",
                    genreIds = listOf(1, 3),
                    popularity = 2.1,
                    releaseDate = "02/05/2012",
                    video = false,
                    voteAverage = 3.5,
                    voteCount = 678
                ),
                Movie(
                    id = 2,
                    title = "Movie 2",
                    originalTitle = null,
                    overview = "Good Movie",
                    posterPath = null,
                    backdropPath = null,
                    mediaType = "VHS",
                    adult = true,
                    originalLanguage = "English",
                    genreIds = listOf(1, 3),
                    popularity = 2.1,
                    releaseDate = "02/05/2012",
                    video = false,
                    voteAverage = 3.5,
                    voteCount = 678
                )
            ),
            status = MovieResult.Status.SUCCESS
        )
        coEvery { getPopularMoviesUseCase.invoke() } returns movieResult

        // When
        viewModel.onAction(MovieListIntent.Initial)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        // Then
        val expectedState = MovieListState(
            isLoading = IsLoading(false),
            page = Page(1),
            movies = movieResult.movies
        )
        assertEquals(expectedState.movies.size, viewModel.state.movies.size)
        assertEquals(expectedState.isLoading, viewModel.state.isLoading)
    }
}