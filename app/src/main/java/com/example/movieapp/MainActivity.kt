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
import com.example.movieapp.data.remote_service.RetrofitInstance
import com.example.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Your TMDB API key (replace this with a real key)
        val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YzA3M2ViNjc1ZGNlZTY0NDYxMjQ1M2Q3ZWEwNzM2NiIsIm5iZiI6MTc0MjQxOTkxNS4xMzMsInN1YiI6IjY3ZGIzN2NiOWYzNThmNGExMzdmODU0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.332uWrq_Gil3_6882D-UJwe2RUwKJ5PU9BiNgw6DcL8"

        // Create API service
        val movieService = RetrofitInstance.createMovieService()

        // Fetch movies in a coroutine
        lifecycleScope.launch {
            try {
                val response = movieService.getPopularMovies()
                Log.d("MovieAPI", "Movies fetched: ${response.results}")
            } catch (e: Exception) {
                Log.e("MovieAPI", "Error fetching movies: ${e.message}")
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