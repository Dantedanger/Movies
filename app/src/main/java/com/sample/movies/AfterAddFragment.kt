package com.sample.movies

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.squareup.picasso.Picasso
import org.json.JSONObject.NULL
import java.util.concurrent.TimeUnit

class AfterAddFragment : Fragment() {
    interface Callbacks {
        fun onSearch()
        fun onAdd()
    }

    private lateinit var titleField: EditText
    private lateinit var dateButton: EditText
    private lateinit var searchButton: Button
    private lateinit var addButton: Button
    private lateinit var id: String
    private lateinit var url: String
    private lateinit var title: String
    private lateinit var year: String
    private var callbacks: Callbacks? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        id = arguments?.getString("galleryItemId").toString()
        url = arguments?.getString("galleryItemUrl").toString()
        title = arguments?.getString("galleryItemTitle").toString()
        year = arguments?.getString("galleryItemYear").toString()
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.after_add, container, false)
        titleField = view.findViewById(R.id.movie_title) as EditText
        dateButton = view.findViewById(R.id.year) as EditText
        searchButton = view.findViewById(R.id.search_button) as Button
        addButton = view.findViewById(R.id.add) as Button
        searchButton.setOnClickListener {
            callbacks?.onSearch()
        }
        addButton.setOnClickListener {
            titleField.setText(title)
            dateButton.setText(year)
            callbacks?.onAdd()
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance() = BeforeAddFragment()
    }
}