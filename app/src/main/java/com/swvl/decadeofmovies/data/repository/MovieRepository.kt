package com.swvl.decadeofmovies.data.repository

import androidx.lifecycle.LiveData
import com.swvl.decadeofmovies.data.model.Movie

interface MovieRepository {

    fun addMovies(movie: List<Movie>)
    fun getMovies(): LiveData<List<Movie>>
    fun getLocalMovies(): LiveData<List<Movie>>
    fun getMoviesByName(movie_name: String): LiveData<List<Movie>>
}