package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.example.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.example.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var getPopularMoviesUseCase:
            GetPopularMoviesUseCase

    @Inject lateinit var getMovieDetailUseCase:
            GetMovieDetailsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fetch movies in a coroutine
        lifecycleScope.launch {
            try {
                val response = getPopularMoviesUseCase.invoke();
                Log.d("MovieAPI", "Movies fetched: $response")
            } catch (e: Exception) {
                Log.e("MovieAPI", "Error fetching movies: ${e.message}")
            }
        }

        lifecycleScope.launch {
            try {
                val response = getMovieDetailUseCase.invoke();
                Log.d("MovieAPI", "Movie details fetched: $response")
            }
            catch (e: Exception) {
                Log.e("MovieAPI", "Error fetching movie details: ${e.message}")
            }
        }

        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//@Composable
//fun MovieNavGraph() {
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = "movie_list") {
//        composable("movie_list") { MovieListScreen(viewModel = MovieViewModel(), navController) }
//        composable("movie_detail/{movieId}") { backStackEntry ->
//            MovieDetailScreen(movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: 0)
//        }
//    }
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}