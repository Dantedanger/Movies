package com.sample.movies.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface OmdbApi {
    @GET("?t=")
    fun searchMovies(@Query("text") query: String): Call<OmdbResponse>
}