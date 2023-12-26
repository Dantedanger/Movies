package com.sample.movies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private const val TAG = "SearchActivity"
class SearchActivity : AppCompatActivity(), SearchFragment.Callbacks{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val title = intent.getStringExtra("addgalleryItemTitle")
        val year = intent.getStringExtra("addgalleryItemYear")
        val isFragmentContainerEmpty =
            savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.searchFragmentContainer, SearchFragment.newInstance(title.toString(),year.toString()))
                .commit()
        }
    }
    override fun onSelected(galleryItem: GalleryItem){
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("fragmentToOpen", "lastFragment")
        intent.putExtra("galleryItemId", galleryItem.id)
        intent.putExtra("galleryItemUrl", galleryItem.url)
        intent.putExtra("galleryItemYear", galleryItem.year)
        intent.putExtra("galleryItemTitle", galleryItem.title)
        startActivity(intent)
    }

    companion object {
        fun newIntent(context: Context): Intent
        {
            return Intent(context, SearchActivity::class.java)
        }
    }

}