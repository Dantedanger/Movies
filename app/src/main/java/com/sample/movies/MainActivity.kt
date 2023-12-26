package com.sample.movies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity(), MovieGalleryFragment.Callbacks  {
    private lateinit var movieGalleryViewModel: MovieGalleryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieGalleryViewModel = ViewModelProviders.of(this).get(MovieGalleryViewModel::class.java)
        val isFragmentContainerEmpty =
            savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerMain, MovieGalleryFragment.newInstance())
                .commit()
        }
    }
    override fun noItems()
    {
        val fragment = MainNotFoundFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerMain, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newIntent(context: Context): Intent
        {
            return Intent(context, MainActivity::class.java)
        }
    }
}