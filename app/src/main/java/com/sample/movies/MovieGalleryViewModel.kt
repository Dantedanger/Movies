package com.sample.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.switchMap
import com.sample.movies.api.OmbdFetchr
import org.json.JSONObject.NULL

class MovieGalleryViewModel(private val app: Application
) : AndroidViewModel(app) {
    private val galleryRepository = GalleryRepository.get()
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    val itemLiveData: LiveData<List<Item>> = galleryRepository.getMovies()

    private val omdbFetchr = OmbdFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()
    private val mutableSearchTerms: MutableLiveData<Pair<String, String>> = MutableLiveData()

    init {
        if (QueryPreferences.getStoredQueryByYear(app) == NULL){
            mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)
            galleryItemLiveData = mutableSearchTerm.switchMap { searchTerm ->
                omdbFetchr.searchMovies(searchTerm)
                }
        }
        else{
            mutableSearchTerms.value = Pair(QueryPreferences.getStoredQuery(app), QueryPreferences.getStoredQueryByYear(app))
            galleryItemLiveData = mutableSearchTerms.switchMap { (searchTerm1, searchTerm2) ->
                omdbFetchr.searchMoviesByYear(searchTerm1, searchTerm2)
                }
            }
        }

    fun fetchMovies(query: String = "") {
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }
    fun fetchMoviesByYear(query: String = "", year: String = "") {
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }
    fun addMovie(movie: GalleryItem) {
        galleryRepository.addMovie(movie)
    }
    fun deleteMovie() {
        galleryRepository.deleteMovie()
    }
}