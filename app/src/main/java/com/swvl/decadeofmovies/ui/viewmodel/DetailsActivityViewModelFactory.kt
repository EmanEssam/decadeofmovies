package com.swvl.decadeofmovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swvl.decadeofmovies.data.repository.PhotosRepository


class DetailsActivityViewModelFactory(private val photosRepository: PhotosRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsActivityViewModel(photosRepository) as T
    }
}