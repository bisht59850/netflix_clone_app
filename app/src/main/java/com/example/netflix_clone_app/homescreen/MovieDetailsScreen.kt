package com.example.netflix_clone_app.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.netflix_clone_app.R
import com.example.netflix_clone_app.api.Movie


@Composable
fun MovieDetailsScreen(
    movieId: String?,
    navController: NavHostController
) {
    val movieDetailsViewModel: MovieDetailsViewModel = viewModel()
    val movieDetails = movieDetailsViewModel.movieDetails.collectAsState().value

    movieId?.let {
        movieDetailsViewModel.fetchMovieDetails(it.toInt())
    }

    movieDetails?.let { movie ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.TopCenter
        ) {
            MovieDetailsContent(movie = movie)
        }
    }
}


@Composable
fun MovieDetailsContent(movie: Movie) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            TopBarDetailScreen()
        }
        item {
            //Poster
            Image(
                painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .size(200.dp, 300.dp),
                contentScale = ContentScale.Crop
            )
        }
        item {
            //title
            Text(
                text = movie.title ?: "Title not available",
                color = Color.White,
                fontSize = 24.sp
            )
        }
        item {
            //Release_Date
            Text(
                text = "Release Date: ${movie.release_date}",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.overview ?: "No overview available.",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun TopBarDetailScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.netflix_logo),
            contentDescription = "Netflix Logo",
            modifier = Modifier.size(50.dp)
        )
    }
}


//suspend fun fetchMovieDetails(movieId: String?): Movie? {
//    return if (movieId != null) { // Check if movieId is not null
//        try {
//            val response = RetrofitInstance.api.getMovieDetails(movieId.toInt(), "93595c8d35bad74f8dfd42cf550768d9")
//            response
//        } catch (e: Exception) {
//            null // Handle the error gracefully
//        }
//    } else {
//        null
//    }
//}