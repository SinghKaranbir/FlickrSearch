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
        viewModel.searchPhoto(DEFAULT_SEARCH)
        viewModel.searchResult.observe(this, Observer {
            adapter?.submitList(it)
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

    companion object {
        private const val DEFAULT_SEARCH = "seascape"
    }
}