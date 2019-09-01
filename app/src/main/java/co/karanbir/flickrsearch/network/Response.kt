package co.karanbir.flickrsearch.network

import com.google.gson.annotations.Expose

data class Response(
    @Expose
    var photos: Photos
)

data class Photos(
    @Expose
    var perpage: String? = null,

    @Expose
    var total: String? = null,

    @Expose
    var pages: String? = null,

    @Expose
    var photo: List<Photo> = arrayListOf()
)

data class Photo(
    @Expose
    var owner: String? = null,

    @Expose
    var server: String? = null,

    @Expose
    var height_s: String? = null,

    @Expose
    var width_s: String? = null,

    @Expose
    var url_s: String? = null,

    @Expose
    var ispublic: String? = null,

    @Expose
    var isfriend: String? = null,

    @Expose
    var farm: String? = null,

    @Expose
    var id: String? = null,

    @Expose
    var secret: String? = null,

    @Expose
    var title: String? = null,

    @Expose
    var isfamily: String? = null
)