package com.swvl.decadeofmovies.data.service

import com.swvl.decadeofmovies.data.model.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET(value = "?method=flickr.photos.getRecent")
    fun getPhotos(
        @Query(value = "api_key") api_key: String,
        @Query(value = "format") format: String,
        @Query(value = "nojsoncallback") noJson: Int,
        @Query(value = "text") movie_name: String,
        @Query(value = "page") page: Int,
        @Query(value = "per_page") per_page: Int
    ): Call<Photo>

}