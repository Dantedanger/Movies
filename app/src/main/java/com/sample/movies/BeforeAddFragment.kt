package com.sample.movies

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class BeforeAddFragment : Fragment() {
    interface Callbacks {
        fun onSearch(title: String, year: String)
        fun onAddBefore()
    }
    private lateinit var titleField: EditText
    private lateinit var dateButton: EditText
    private lateinit var searchButton: Button
    private lateinit var addButton: Button
    private var callbacks: Callbacks? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.before_add, container, false)
        titleField = view.findViewById(R.id.movie_title) as EditText
        dateButton = view.findViewById(R.id.year) as EditText
        searchButton = view.findViewById(R.id.search_button) as Button
        addButton = view.findViewById(R.id.button2) as Button
        searchButton.setOnClickListener {
            callbacks?.onSearch(titleField.text.toString(),dateButton.text.toString())
        }
        addButton.setOnClickListener {
            callbacks?.onAddBefore()
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