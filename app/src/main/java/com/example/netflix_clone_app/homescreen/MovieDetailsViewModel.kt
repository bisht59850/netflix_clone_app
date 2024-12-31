package com.example.netflix_clone_app.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflix_clone_app.api.Movie
import com.example.netflix_clone_app.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {
    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails: StateFlow<Movie?> = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getMovieDetails(movieId, "93595c8d35bad74f8dfd42cf550768d9")
                _movieDetails.value = response
            }
            catch (e: Exception) {
                _movieDetails.value = null
            }
        }
    }
}