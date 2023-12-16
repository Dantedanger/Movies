package com.sample.movies

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}