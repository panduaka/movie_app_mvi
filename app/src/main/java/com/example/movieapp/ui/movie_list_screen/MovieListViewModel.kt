package com.example.movieapp.ui.movie_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    /**
     * Coming from Coroutines API
     *
     * These are part of Kotlin Coroutines' Flow API, designed for asynchronous data streams.
     * MutableStateFlow is a state holder that can emit new values.
     * StateFlow is a read-only flow that exposes the latest value emitted by a MutableStateFlow.
     * They are excellent for complex asynchronous scenarios and reactive programming patterns.
     * Updates are done by assigning a new value to the value property of the MutableStateFlow.
     */
//    private val _state = MutableStateFlow<MovieListState>(MovieListState.Loading)
//    val state: StateFlow<MovieListState> = _state.asStateFlow()

    /**
     * This is part of Jetpack Compose's state management system.
     * It's specifically designed to trigger recomposition in Compose when the value changes.
     * Updates are done by assigning a new value to the variable.
     * It's typically used for state that directly affects the UI in a Compose-based application.
     * It is a simple solution for UI state management, not for complex asynchronous operations
     */
    var state by mutableStateOf<MovieListState>(MovieListState())
        private set

    init {
        onAction(MovieListIntent.Initial)
    }

   fun onAction(intent: MovieListIntent) {
        when (intent) {
            is MovieListIntent.Initial -> fetchMovies()
            is MovieListIntent.SelectMovie -> selectMovie(intent.movieId)
        }
    }

    private fun selectMovie(movieId: Int) {
        state = state.copy(
            goToMovieDetails = MovieDetail(movieId)
        )
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val moviesResult = getPopularMoviesUseCase.invoke()
                if (moviesResult.status == MovieResult.Status.LOADING) {
                    state = state.copy(
                        isLoading = IsLoading(true)
                    )
                }
                else if (moviesResult.movies.isEmpty()) {
                    state = state.copy(
                        isLoading = IsLoading(false),
                        error = Error("No movies found")
                    )
                } else {
                    state = state.copy(
                        isLoading = IsLoading(false),
                        page = Page(1),
                        movies = moviesResult.movies
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = IsLoading(false),
                    error = Error(e.message ?: "Unknown error")
                )
            }
        }
    }
}