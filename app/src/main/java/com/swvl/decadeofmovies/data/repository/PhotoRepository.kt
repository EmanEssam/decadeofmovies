package com.swvl.decadeofmovies.data.repository

import androidx.lifecycle.MutableLiveData
import com.swvl.decadeofmovies.data.model.Photo
import com.swvl.decadeofmovies.data.service.FlickrApiService

interface PhotosRepository {

    fun getPhotosFromAPi(movieName: String, photoService: FlickrApiService): MutableLiveData<Photo>
}