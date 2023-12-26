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
import android.widget.CheckBox
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

class SearchFragment : Fragment() {
    interface Callbacks {
        fun onSelected(galleryItem: GalleryItem)
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
        val view = inflater.inflate(R.layout.search_recycler, container, false)
        movieRecyclerView = view.findViewById(R.id.movie_recycler_view)
        movieRecyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieGalleryViewModel.galleryItemLiveData.observe(
            viewLifecycleOwner,
            Observer { galleryItems ->
                galleryItems?.let {
                    movieRecyclerView.adapter = MovieAdapter(galleryItems)
                }
            })

    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class MovieHolder(view: View, itemImageView: ImageView) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var galleryItem: GalleryItem
        val bindImageView: (ImageView) = itemImageView
        private val titleTextView: TextView = itemView.findViewById(R.id.movie_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.year)
        init {
            view.setOnClickListener(this)
        }
        fun bind(galleryItem: GalleryItem) {
            this.galleryItem = galleryItem
            titleTextView.text = this.galleryItem.title
            dateTextView.text = this.galleryItem.year
        }
        override fun onClick(v: View) {
            Toast.makeText(
                context,
                "Выбран фильм:${galleryItem.title}",
                Toast.LENGTH_SHORT
            ).show()
            callbacks?.onSelected(galleryItem)
        }
    }

    private inner class MovieAdapter(private val galleryItems: List<GalleryItem>) : RecyclerView.Adapter<MovieHolder>()  {

        @SuppressLint("ResourceType")
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MovieHolder {
            val view = layoutInflater.inflate(
                R.layout.search_item,
                parent,
                false
            ) as View
            val imageView = layoutInflater.inflate(
                R.id.imageView,
                parent,
                false
            ) as ImageView
            return MovieHolder(view,imageView)
        }
        override fun getItemCount(): Int = galleryItems.size
        override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            val galleryItem = galleryItems[position]
            Picasso.get()
                .load(galleryItem.url)
                .placeholder(R.drawable.bill_up_close)
                .into(holder.bindImageView)
            holder.bind(galleryItem)
        }
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}