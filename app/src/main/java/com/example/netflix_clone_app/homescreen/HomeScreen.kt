package com.example.netflix_clone_app.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.netflix_clone_app.R
import com.example.netflix_clone_app.api.Movie


@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsState().value
    val popularMovies = viewModel.popularMovies.collectAsState().value
    val topRatedMovies = viewModel.topRatedMovies.collectAsState().value
    val upcomingMovies = viewModel.upcomingMovies.collectAsState().value
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        TopBar()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        item {
            MovieSectionForMovies(
                title = "Now Playing",
                movies = nowPlayingMovies,
                navController = navController
            )
        }
        item {
            MovieSectionForMovies(
                title = "Popular",
                movies = popularMovies,
                navController = navController
            )
        }
        item {
            MovieSectionForMovies(
                title = "Top Rated",
                movies = topRatedMovies,
                navController = navController
            )
        }
        item {
            MovieSectionForMovies(
                title = "Upcoming",
                movies = upcomingMovies,
                navController = navController
            )
        }

        item {
            MovieSectionForMovies(
                title = "Now Playing",
                movies = nowPlayingMovies,
                navController = navController
            )
        }
        item {
            MovieSectionForMovies(
                title = "Popular",
                movies = popularMovies,
                navController = navController
            )
        }
        item {
            MovieSectionForMovies(
                title = "Top Rated",
                movies = topRatedMovies,
                navController = navController
            )
        }
        item {
            MovieSectionForMovies(
                title = "Upcoming",
                movies = upcomingMovies,
                navController = navController
            )
        }
    }
}
}

@Composable
fun MovieSectionForMovies(title: String, movies: List<Movie>, navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = title, color = Color.White, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie, navController = navController) // Pass navController here
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, navController: NavHostController) {
        Image(
        painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
        contentDescription = "Movie Poster",
        modifier = Modifier
            .size(120.dp, 154.dp)
            .background(Color.Gray)
            .clickable {
                                navController.navigate("movie_details_screen/${movie.id}")
            },
        contentScale = ContentScale.Crop
    )
}



@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.netflix_logo),
            contentDescription = "Netflix Logo",
            modifier = Modifier.size(50.dp)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "TV Shows", color = Color.White, fontSize = 14.sp)
            Text(text = "Movies", color = Color.White, fontSize = 14.sp)
            Text(text = "My List", color = Color.White, fontSize = 14.sp)
        }
    }
}

@Preview
@Composable
fun DisplayHomeScreen(){
    HomeScreen(navController = NavHostController(LocalContext.current))
}

