package com.swvl.decadeofmovies.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swvl.decadeofmovies.data.database.MovieDao
import com.swvl.decadeofmovies.data.database.MovieDatabase
import com.swvl.decadeofmovies.data.model.Movie
import com.swvl.decadeofmovies.data.model.MovieData

class MovieRepositoryImpl(private val movieDao: MovieDao, private val context: Context) : MovieRepository {
    val database: MovieDatabase = MovieDatabase.getInstance(context)!!


    override fun getMoviesByName(movie_name: String): LiveData<List<Movie>> {
        return database.movieDao().getMoviesByName(movie_name)

    }

    private var moviesList = mutableListOf<Movie>()
    private val movies = MutableLiveData<List<Movie>>()


    override fun addMovies(movie: List<Movie>) {
        movieDao.insertAll(movie)
    }



    override fun getLocalMovies(): LiveData<List<Movie>> {
        val movieJsonData = MovieData.readMovies(context)
        moviesList = MovieData.parseMovies(movieJsonData)
        movies.value = moviesList
        return movies
    }

    override fun getMovies(): LiveData<List<Movie>> {
        return movieDao.getAll()
    }


}

