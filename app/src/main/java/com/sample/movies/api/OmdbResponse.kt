package com.sample.movies.api

import com.sample.movies.MovieResponse

data class OmdbResponse(
    val Search: List<MovieItem>, // Используйте List<MovieItem> вместо MovieItem
    val totalResults: String,
    val Response: String
)

data class MovieItem(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)