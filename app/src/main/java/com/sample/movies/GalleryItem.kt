package com.sample.movies

import com.google.gson.annotations.SerializedName

data class GalleryItem(
    var title: String = "",
    var year: String = "",
    var type: String = "",
    var id: String = "",
    @SerializedName("i") var url: String = ""
)