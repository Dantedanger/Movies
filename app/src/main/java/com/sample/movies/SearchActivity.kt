package com.sample.movies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

private const val TAG = "SearchActivity"
class SearchActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
//        val isFragmentContainerEmpty =
//            savedInstanceState == null
//        if (isFragmentContainerEmpty) {
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.fragmentContainer, MovieGalleryFragment.newInstance())
//                .commit()
//        }
    }

    companion object {
        fun newIntent(context: Context): Intent
        {
            return Intent(context, SearchActivity::class.java)
        }
    }

}