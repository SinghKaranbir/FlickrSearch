package co.karanbir.flickrsearch.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.karanbir.flickrsearch.network.Photo


class PhotoListAdapter(private val context: Context) :
    PagedListAdapter<Photo, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotosViewHolder.create(context, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        repoItem?.let { (holder as PhotosViewHolder).bind(repoItem) }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }
}