package com.swvl.decadeofmovies.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.swvl.decadeofmovies.R
import com.swvl.decadeofmovies.data.model.Movie
import com.swvl.decadeofmovies.utils.MOVIE_KEY
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_detail)

        setSupportActionBar(detail_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie: Movie = intent.extras.get(MOVIE_KEY) as Movie

        title = movie.title



        if (savedInstanceState == null) {


            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        MovieDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(MovieDetailFragment.ARG_ITEM_ID)

                    )
                    putSerializable(MOVIE_KEY, movie)

                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {


                navigateUpTo(Intent(this, MovieListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
