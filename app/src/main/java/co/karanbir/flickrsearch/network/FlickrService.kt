package co.karanbir.flickrsearch.network

import co.karanbir.flickrsearch.BuildConfig
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    @GET("/services/rest/")
    fun searchPhotos(
        @Query("method") method: String = SEARCH_METHOD,
        @Query("api_key") key: String = BuildConfig.FLICKR_API_KEY,
        @Query("text") searchTag: String,
        @Query("per_page") perPage: Int,
        @Query("page") pageNo: Int,
        @Query("extras") extras: String = "url_s",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: String = "1"
    ): Single<Response>


    companion object {
        private const val BASE_URL = "https://api.flickr.com"
        private const val SEARCH_METHOD = "flickr.photos.search"


        fun getService(): FlickrService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(FlickrService::class.java)
        }
    }
}