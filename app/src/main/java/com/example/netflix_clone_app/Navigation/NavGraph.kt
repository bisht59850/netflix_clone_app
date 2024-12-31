package com.example.netflix_clone_app.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.netflix_clone_app.SplashScreen.SplashScreen
import com.example.netflix_clone_app.homescreen.HomeScreen
import com.example.netflix_clone_app.homescreen.MovieDetailsScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("home_screen") {
            HomeScreen(navController = navController)
        }
        composable("movie_details_screen/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            MovieDetailsScreen(movieId = movieId ?: "", navController = navController)
        }
    }
}
