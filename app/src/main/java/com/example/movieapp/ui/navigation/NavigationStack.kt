package com.example.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.movie_detail_screen.MovieDetailScreen
import com.example.movieapp.ui.movie_list_screen.MovieListScreen

@Composable
fun NavigationStack(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MovieList.route) {
        composable(route = Screen.MovieList.route) {
            MovieListScreen(navController = navController)
        }
        composable(
            route = Screen.MovieDetail.route + "?movieId={movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            MovieDetailScreen(movieId = it.arguments?.getString("movieId"))
        }
    }
}