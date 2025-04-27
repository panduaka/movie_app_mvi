package com.example.movieapp.ui.movie_list_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel(),
    modifier: Modifier
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Trending Movies") }
            )
        }
    ) { paddingValues ->

        MovieListCoreScreen(
            modifier = Modifier.padding(paddingValues),
            state = viewModel.state,
            onAction = viewModel::onAction,
            navController = navController
        )
    }
}

@Composable
fun MovieListCoreScreen(
    modifier: Modifier = Modifier,
    state: MovieListState,
    onAction: (MovieListIntent) -> Unit,
    navController: NavController,
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading.isLoading) {
            LoadingScreen(modifier = Modifier.align(Alignment.Center))
        }
        if (state.error.error.isNotBlank()) {
            ErrorScreen(message = state.error.error, modifier = Modifier.align(Alignment.Center))
        }
        if (state.movies.isNotEmpty()) {
            MovieList(
                modifier = Modifier.fillMaxSize(),
                movies = state.movies,
                onMovieClick = { movieId ->
                    onAction(MovieListIntent.SelectMovie(movieId))
                }
            )
        }
        if (state.goToMovieDetails != null) {
            LaunchedEffect(key1 = true) {
                navController.navigate(Screen.MovieDetail.route + "?movieId=${state.goToMovieDetails.movieId}")
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun MovieList(movies: List<Movie>, onMovieClick: (Int) -> Unit, modifier: Modifier) {
    LazyColumn(
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onMovieClick = onMovieClick
            )
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onMovieClick(movie.id)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Movie Poster
            MoviePoster(posterPath = movie.posterPath)

            Spacer(modifier = Modifier.width(8.dp))

            // Movie Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (!movie.originalTitle.isNullOrBlank() && movie.originalTitle != movie.title) {
                    Text(
                        text = "(${movie.originalTitle})",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MoviePoster(posterPath: String?) {
    val imageUrl = "https://image.tmdb.org/t/p/w185$posterPath" // Example base URL
    if (!posterPath.isNullOrBlank()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground), // Replace with your placeholder
            contentDescription = "Movie Poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )
    } else {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground), // Replace with your placeholder
            contentDescription = "Movie Poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun ErrorScreen(message: String, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error: $message")
    }
}