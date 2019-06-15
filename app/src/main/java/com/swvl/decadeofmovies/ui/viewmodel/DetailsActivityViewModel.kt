package com.swvl.decadeofmovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.swvl.decadeofmovies.data.repository.PhotosRepository

class DetailsActivityViewModel(private val photoRepository: PhotosRepository) : ViewModel() {
    fun getPhotosFromApi(title: String) = photoRepository.getPhotosFromAPi(title)

}