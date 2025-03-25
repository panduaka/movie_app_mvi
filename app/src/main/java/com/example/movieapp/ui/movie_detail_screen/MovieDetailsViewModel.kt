package com.example.movieapp.ui.movie_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _state.update { MovieDetailState.Loading }
            try {
                val movieDetail = getMovieDetailUseCase.invoke(
                    movieId = movieId,
                    language = "en-US"
                )
                _state.update { MovieDetailState.Success(movieDetail) }
            } catch (e: Exception) {
                _state.update { MovieDetailState.Error(e.message ?: "Unknown error") }
            }
        }
    }
}