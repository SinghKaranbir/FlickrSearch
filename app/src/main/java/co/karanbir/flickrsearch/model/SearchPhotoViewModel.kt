package co.karanbir.flickrsearch.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import co.karanbir.flickrsearch.data.FlickrRepository
import co.karanbir.flickrsearch.network.FlickrService
import co.karanbir.flickrsearch.network.Photo
import io.reactivex.disposables.CompositeDisposable

class SearchPhotoViewModel : ViewModel() {
    private val queryLiveData = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()
    private val flickrService = FlickrService.getService()
    private val flickrRepository: FlickrRepository

    init {
        flickrRepository = FlickrRepository(flickrService, compositeDisposable)
    }

    val searchResult: LiveData<PagedList<Photo>> = Transformations.switchMap(queryLiveData) {
        flickrRepository.searchPhotos(it)
    }


    fun searchPhoto(queryString: String) {
        queryLiveData.postValue(queryString)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}