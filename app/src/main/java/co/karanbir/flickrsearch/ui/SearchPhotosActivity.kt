package co.karanbir.flickrsearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import co.karanbir.flickrsearch.R
import co.karanbir.flickrsearch.model.SearchPhotoViewModel
import co.karanbir.flickrsearch.ui.adapter.PhotoListAdapter
import kotlinx.android.synthetic.main.activity_search_photos.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.content.Context
import android.view.inputmethod.InputMethodManager


class SearchPhotosActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchPhotoViewModel
    private var adapter: PhotoListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_photos)
        //Init Recycler View
        initRecycleView()

        //Init the View Model
        viewModel = ViewModelProviders.of(this)
            .get(SearchPhotoViewModel::class.java)
        viewModel.searchResult.observe(this, Observer {
            adapter?.submitList(it)
        })

        //perform default search
        performSearch(DEFAULT_SEARCH)


        //Perform Searching operation
        et_search_tag?.setText(DEFAULT_SEARCH)
        et_search_tag?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val searchTag = et_search_tag.text.trim()
                    if (searchTag.isNotEmpty()) {
                        rv_photos?.scrollToPosition(0)
                        adapter?.submitList(null)
                        performSearch(searchTag.toString())

                        currentFocus?.let {
                            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputManager.hideSoftInputFromWindow(it.windowToken, HIDE_NOT_ALWAYS)
                        }
                    }

                    return true
                }

                return false
            }
        })
    }

    private fun initRecycleView() {
        adapter = PhotoListAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        rv_photos?.also {
            it.layoutManager = layoutManager
            it.adapter = adapter
            it.addItemDecoration(decoration)
        }
    }

    private fun performSearch(searchTag: String) {
        viewModel.searchPhoto(searchTag)
    }

    companion object {
        private const val DEFAULT_SEARCH = "seascape"
    }
}