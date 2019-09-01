package co.karanbir.flickrsearch.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import co.karanbir.flickrsearch.network.FlickrService
import co.karanbir.flickrsearch.network.Photo
import io.reactivex.disposables.CompositeDisposable

class FlickrDataSourceFactory(
    private val searchTag: String,
    private val compositeDisposable: CompositeDisposable,
    private val flickrService: FlickrService) : DataSource.Factory<Int, Photo>() {

    private val flickrDataSourceLiveData = MutableLiveData<FlickrDataSource>()

    override fun create(): DataSource<Int, Photo> {
        val flickrDataSource = FlickrDataSource(searchTag, flickrService, compositeDisposable)
        flickrDataSourceLiveData.postValue(flickrDataSource)
        return flickrDataSource
    }
}