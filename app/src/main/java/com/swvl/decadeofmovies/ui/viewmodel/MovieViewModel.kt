package com.swvl.decadeofmovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.swvl.decadeofmovies.data.repository.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {


    fun getLocalMovies() = movieRepository.getLocalMovies()

    fun getMoviesByName(movie_name: String) = movieRepository.getMoviesByName(movie_name)
}