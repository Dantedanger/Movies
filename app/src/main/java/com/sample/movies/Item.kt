package com.sample.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "gallery")

data class Item(
    @PrimaryKey var id: String = "",
    var title: String = "",
    var year: String = "",
    @SerializedName("i") var url: String = "",
    var delete: Boolean = false
)