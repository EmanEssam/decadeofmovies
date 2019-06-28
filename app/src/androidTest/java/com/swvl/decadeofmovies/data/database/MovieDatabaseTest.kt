package com.swvl.decadeofmovies.data.database

import android.os.Handler
import android.os.Looper
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.swvl.decadeofmovies.data.model.Movie
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDatabaseTest {

    private var movieDao: MovieDao? = null

    private var movieDatabase: MovieDatabase? = null

    @Before
    fun setUp() {

        movieDatabase = MovieDatabase.getInstance(InstrumentationRegistry.getTargetContext())!!

        movieDao = movieDatabase!!.movieDao()


    }

    @Test
    fun insert_movies_successfully() {

        val movies = listOf(

            Movie(0, "fight club", "1999", listOf("name", "name"), listOf("comedy"), 5)
            , Movie(0, "fight club", "1999", listOf("name", "name"), listOf("comedy"), 5)
            , Movie(0, "fight club", "1999", listOf("name", "name"), listOf("comedy"), 5)
            , Movie(0, "fight club", "1999", listOf("name", "name"), listOf("comedy"), 5)
        )

        val result = movieDao!!.insertAll(movies)

        assertNotNull(result)

    }

    @Test
    @Throws(Exception::class)
    fun getMovieByName() {

        Handler(Looper.getMainLooper()).post {
            val movieWrappedInLiveData = movieDatabase!!.movieDao().getMoviesByName("fight").observeForever {
                assertNotNull(it)
                assertTrue(it.size <= 5)

            }


        }
    }


    @After
    fun tearDown() {

        movieDatabase!!.close()

    }
}