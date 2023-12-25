package com.sample.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.photogallery.Item
import com.sample.photogallery.database.GalleryDao

@Database(entities = [Item::class], version = 1)
abstract class GalleryDatabase: RoomDatabase() {
    abstract fun galleryDao(): GalleryDao
}