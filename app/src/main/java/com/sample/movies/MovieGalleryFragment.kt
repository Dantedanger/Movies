package com.sample.movies

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import java.util.concurrent.TimeUnit

class MovieGalleryFragment : Fragment() {
    /**
     * Требуемый интерфейс
     */
    interface Callbacks {
        fun onDeleteSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var movieGalleryViewModel: MovieGalleryViewModel
    private lateinit var movieRecyclerView: RecyclerView
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        movieGalleryViewModel =
            ViewModelProviders.of(this).get(MovieGalleryViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_recycler, container, false)
        movieRecyclerView = view.findViewById(R.id.main_movie_recycler_view)
        movieRecyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }
    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieGalleryViewModel.itemLiveData.observe(
            viewLifecycleOwner,
            Observer { items ->
                movieRecyclerView.adapter =
                    MovieAdapter(items)
            })
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val bindView: (View) = view
        private lateinit var item: Item
        init {

        }
        fun bind(item: Item) {
            this.item = item
        }

        override fun onClick(v: View?) {
            item.delete = true
        }
    }

    private inner class MovieAdapter(private val items: List<Item>)
        : RecyclerView.Adapter<MovieHolder>()  {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MovieHolder {
            val view = layoutInflater.inflate(
                R.layout.main_movies_watch,
                parent,
                false
            ) as ImageView
            return MovieHolder(view)
        }
        override fun getItemCount(): Int = items.size
        override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_delete_database_movies -> {
                callbacks?.onDeleteSelected()
                Toast.makeText(
                    context,
                    R.string.delete_movies_from_database_success,
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun newInstance() = MovieGalleryFragment()
    }
}