package com.swvl.decadeofmovies.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.swvl.decadeofmovies.data.model.Movie
import com.swvl.decadeofmovies.data.repository.MovieRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieViewModelTest {


    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository


    private var movie: MutableLiveData<List<Movie>> = MutableLiveData()

    @Mock
    lateinit var context: Context


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        context = mock(Context::class.java)

        movieViewModel = MovieViewModel(movieRepository)

        val movies = listOf(Movie(0, "fight club", "", emptyList(), emptyList(), 1))

        movie.value = movies


    }

    @Test
    fun validateLocalMoviesData_isNotEmpty_ReturnTrue() {


        `when`(movieRepository.getLocalMovies()).thenReturn(movie)

        val result = movieViewModel.getLocalMovies()

        assertTrue(result.value!!.isNotEmpty())

    }
}