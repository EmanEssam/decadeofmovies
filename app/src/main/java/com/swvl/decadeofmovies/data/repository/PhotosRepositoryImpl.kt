package com.swvl.decadeofmovies.data.repository

import androidx.lifecycle.MutableLiveData
import com.swvl.decadeofmovies.data.model.Photo
import com.swvl.decadeofmovies.data.service.FlickrApiService
import com.swvl.decadeofmovies.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosRepositoryImpl : PhotosRepository {


    override fun getPhotosFromAPi(movieName: String, photoService: FlickrApiService): MutableLiveData<Photo> {


        val data = MutableLiveData<Photo>()

        photoService.getPhotos(

            API_KEY, FORMAT_PARAMETER, NO_JSON_CALLBACK_PARAMETER,

            movieName, PAGE_PARAMETER, PER_PAGE_PARAMETER

        ).enqueue(object : Callback<Photo> {

            override fun onFailure(call: Call<Photo>, t: Throwable) {

                data.value = null
            }

            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {

                data.value = response.body()
            }
        })

        return data
    }

}
