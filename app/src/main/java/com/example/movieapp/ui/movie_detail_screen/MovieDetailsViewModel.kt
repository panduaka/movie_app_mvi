package com.example.movieapp.ui.movie_detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.example.movieapp.ui.movie_list_screen.IsLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.movieapp.ui.movie_list_screen.Error

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    var state by mutableStateOf<MovieDetailState>(MovieDetailState())
        private set

    fun onAction() {

    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = IsLoading(true),
            )
            try {
                val movieDetail = getMovieDetailUseCase.invoke(
                    movieId = movieId,
                    language = "en-US"
                )
                if (movieDetail.movieDetail == null) {
                    state = state.copy(
                        isLoading = IsLoading(false),
                        error = Error(
                            error = "Movie detail not found"
                        )
                    )
                } else {
                    state = state.copy(
                        isLoading = IsLoading(false),
                        movieDetail = movieDetail.movieDetail
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