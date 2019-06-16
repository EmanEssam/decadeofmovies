package com.swvl.decadeofmovies.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.swvl.decadeofmovies.data.model.Movie

@Dao
interface MovieDao {


    @Query("SELECT * FROM movies")
    fun getAll(): LiveData<List<Movie>>

    @Insert
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movies WHERE title LIKE :movie_name order by rating limit 5")
    fun getMoviesByName(movie_name: String): LiveData<List<Movie>>
}