package co.karanbir.flickrsearch.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import co.karanbir.flickrsearch.network.FlickrService
import co.karanbir.flickrsearch.network.Photo
import io.reactivex.disposables.CompositeDisposable

class FlickrDataSource(
    private val searchTag: String,
    private val flickrService: FlickrService,
    private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, Photo>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {
        compositeDisposable.add(
            flickrService.searchPhotos(
                searchTag = searchTag,
                pageNo = 1,
                perPage = params.requestedLoadSize
            ).subscribe({
                callback.onResult(it.photos.photo, null, 2)
            }, {
                //TODO Add new Model so that we can send the failure case to the UI
                //callback.onResult(Result(state=error), null, 2)
                Log.e(TAG, "Error loading ${params.requestedLoadSize}")
            })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        compositeDisposable.add(
            flickrService.searchPhotos(
                searchTag = searchTag,
                pageNo = params.key,
                perPage = params.requestedLoadSize
            ).subscribe({
                callback.onResult(it.photos.photo, params.key + 1)
            }, {
                //TODO Add new Model so that we can send the failure case to the UI
                Log.e(TAG, "Error loading ${params.requestedLoadSize}")
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        compositeDisposable.add(
            flickrService.searchPhotos(
                searchTag = searchTag,
                pageNo = params.key,
                perPage = params.requestedLoadSize
            ).subscribe({
                callback.onResult(it.photos.photo, params.key - 1)
            }, {
                //TODO Add new Model so that we can send the failure case to the UI
                Log.e(TAG, "Error loading ${params.requestedLoadSize}")
            })
        )
    }

    companion object {
        private const val TAG = "FlickrDataSource"
    }
}