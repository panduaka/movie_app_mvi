package com.example.movieapp.ui.movie_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MovieListState>(MovieListState.Loading)
    val state: StateFlow<MovieListState> = _state.asStateFlow()

    init {
        processIntent(MovieListIntent.Initial)
    }

    private fun processIntent(intent: MovieListIntent) {
        when (intent) {
            MovieListIntent.Initial -> fetchMovies()
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _state.value = MovieListState.Loading
            try {
                val movies = getPopularMoviesUseCase.invoke()
                _state.value = MovieListState.Success(movies)
            } catch (e: Exception) {
                _state.value = MovieListState.Error("Error fetching movies: ${e.message}")
            }
        }
    }
}