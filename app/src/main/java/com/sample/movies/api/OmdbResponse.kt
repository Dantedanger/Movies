package com.sample.movies.api

import com.sample.movies.MovieResponse

data class OmdbResponse(
    val Search: List<MovieItem>, // Используйте List<MovieItem> вместо MovieItem
)

data class MovieItem(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)