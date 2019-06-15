package com.swvl.decadeofmovies.data.repository

import androidx.lifecycle.MutableLiveData
import com.swvl.decadeofmovies.data.model.Photo

interface PhotosRepository {
    fun getPhotosFromAPi(movieName: String): MutableLiveData<Photo>
}