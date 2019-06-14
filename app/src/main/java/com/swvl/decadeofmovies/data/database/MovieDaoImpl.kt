package com.swvl.decadeofmovies.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swvl.decadeofmovies.data.model.Movie

class MovieDaoImpl : MovieDao {
    override fun getMoviesByName(movie_name: String): LiveData<List<Movie>> {
        return quotes
    }

    override fun insertAll(movies: List<Movie>) {

    }


    override fun getAll(): LiveData<List<Movie>> {
        return quotes
    }


//    override fun addMovie(movie: Movie) {
//    }

    private val quoteList = mutableListOf<Movie>()
    private val quotes = MutableLiveData<List<Movie>>()

    init {
        quotes.value = quoteList
    }


}