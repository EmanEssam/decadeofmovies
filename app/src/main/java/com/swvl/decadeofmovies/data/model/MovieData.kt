package com.swvl.decadeofmovies.data.model


import android.content.Context
import com.google.gson.Gson
import java.io.InputStream
import java.nio.charset.Charset

open class MovieData {

    companion object {

        fun readMovies(context: Context): String {
            var json: String = ""
            try {
                val inputStream: InputStream = context.assets.open("movies.json")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                val charset: Charset = Charsets.UTF_8
                json = String(buffer, charset)

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return json
        }


        fun parseMovies(moviesResponse: String): MutableList<Movie> {
            val gson = Gson()
            val movies: MovieResponse = gson.fromJson(moviesResponse, MovieResponse::class.java)
            return (movies.movies as MutableList<Movie>?)!!


        }

    }


}