package com.sample.movies.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.movies.Item

@Dao

interface GalleryDao {
    @Query("SELECT * FROM gallery")
    fun getmovies(): LiveData<List<Item>>
    @Insert
    fun addmovie(item: Item)

    @Query("DELETE FROM gallery WHERE id=(:id)")
    fun deletemovie(id: String)

    @Query("SELECT * FROM gallery WHERE id=(:id)")
    fun getmovie(id: String): Item?
}