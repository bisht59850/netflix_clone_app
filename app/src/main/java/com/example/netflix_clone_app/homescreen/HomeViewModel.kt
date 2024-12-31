package com.example.netflix_clone_app.homescreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflix_clone_app.api.Movie
import com.example.netflix_clone_app.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val apiKey = "93595c8d35bad74f8dfd42cf550768d9" // API key

    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies: StateFlow<List<Movie>> = _nowPlayingMovies

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    private val _topRatedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val topRatedMovies: StateFlow<List<Movie>> = _topRatedMovies

    private val _upcomingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val upcomingMovies: StateFlow<List<Movie>> = _upcomingMovies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _nowPlayingMovies.value = RetrofitInstance.api.getNowPlayingMovies(apiKey).results
            _popularMovies.value = RetrofitInstance.api.getPopularMovies(apiKey).results
            _topRatedMovies.value = RetrofitInstance.api.getTopRatedMovies(apiKey).results
            _upcomingMovies.value = RetrofitInstance.api.getUpcomingMovies(apiKey).results
        }
    }
}