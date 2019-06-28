package com.swvl.decadeofmovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.swvl.decadeofmovies.data.repository.PhotosRepository
import com.swvl.decadeofmovies.data.service.FlickrApiService

class DetailsActivityViewModel(private val photoRepository: PhotosRepository) : ViewModel() {

    fun getPhotosFromApi(title: String, photoService: FlickrApiService) =

        photoRepository.getPhotosFromAPi(title, photoService)

}