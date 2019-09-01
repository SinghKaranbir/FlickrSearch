package co.karanbir.flickrsearch.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.karanbir.flickrsearch.R
import co.karanbir.flickrsearch.network.Photo
import com.bumptech.glide.Glide

/**
 * View Holder for a Photo RecyclerView list item.
 */
class PhotosViewHolder(private val context: Context, view: View) : RecyclerView.ViewHolder(view) {
    private val photoView: ImageView = view.findViewById(R.id.iv_photo)

    fun bind(photo: Photo?) {
        photo?.let {
            Glide.with(context)
                .load(it.url_s)
                .into(photoView)
        }
    }

    companion object {
        fun create(context: Context, parent: ViewGroup): PhotosViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
            return PhotosViewHolder(context, view)
        }
    }
}