package com.example.netflix_clone_app.api

data class Movie(
    val id: Int,
    val title: String?,
    val poster_path: String?,
    val overview: String?,
    val release_date: String?
)


data class MovieResponse(
    val results: List<Movie>
)