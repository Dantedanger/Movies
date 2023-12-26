package com.sample.movies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

class AddActivity : AppCompatActivity(), BeforeAddFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val isFragmentContainerEmpty =
            savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerAdd, BeforeAddFragment.newInstance())
                .commit()
        }
    }
    override fun onStart() {
        super.onStart()
    }

    companion object {
        fun newIntent(context: Context): Intent
        {
            return Intent(context, AddActivity::class.java)
        }
    }

    override fun noItems() {

    }
}