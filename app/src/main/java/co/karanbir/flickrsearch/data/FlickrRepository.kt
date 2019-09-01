package co.karanbir.flickrsearch.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import co.karanbir.flickrsearch.network.FlickrService
import co.karanbir.flickrsearch.network.Photo
import io.reactivex.disposables.CompositeDisposable

class FlickrRepository(private val flickrService: FlickrService, private val compositeDisposable: CompositeDisposable) {

    /**
     * Search for specific Photo in Flickr
     *
     * @param searchTag tag of the photos to be loaded
     *
     * @return LiveData object of PagedList for PagedListAdapter
     */
    fun searchPhotos(searchTag: String): LiveData<PagedList<Photo>> {
        // Get data source factory
        val dataSourceFactory =
            FlickrDataSourceFactory(searchTag, compositeDisposable, flickrService)

        // Get the paged list
        return LivePagedListBuilder(dataSourceFactory, DEFAULT_PAGE_SIZE).build()
    }


    companion object {
        private const val DEFAULT_PAGE_SIZE = 50
    }
}