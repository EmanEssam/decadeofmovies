package com.swvl.decadeofmovies.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.swvl.decadeofmovies.data.model.Photo
import com.swvl.decadeofmovies.data.repository.PhotosRepository
import com.swvl.decadeofmovies.data.service.FlickrApiService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsActivityViewModelTest {

    private lateinit var detailsActivityViewModel: DetailsActivityViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var photoRepository: PhotosRepository


    @Mock
    lateinit var flickrService: FlickrApiService

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        detailsActivityViewModel = DetailsActivityViewModel(photoRepository)

    }

    @Test
    fun getPhotosFromApi() {

        val data = MutableLiveData<Photo>()

        val apiResult = photoRepository.getPhotosFromAPi("", flickrService)

        Mockito.`when`(apiResult).thenReturn(data)

    }
}