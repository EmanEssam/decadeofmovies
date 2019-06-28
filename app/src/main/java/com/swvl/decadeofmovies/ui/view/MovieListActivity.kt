package com.swvl.decadeofmovies.ui.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.swvl.decadeofmovies.R
import com.swvl.decadeofmovies.ui.adapter.MovieAdapter
import com.swvl.decadeofmovies.ui.viewmodel.MovieViewModel
import com.swvl.decadeofmovies.ui.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.movie_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [MovieDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MovieListActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: MovieViewModelFactory by instance()
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var searchView: SearchView

    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        initUI()

    }

    private fun initUI() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MovieViewModel::class.java)

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }


        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getLocalMovies().observe(this, Observer { movies ->
            movieAdapter = MovieAdapter(this, movies, twoPane)
            item_list.adapter = movieAdapter

        })


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "Search By Movie name"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                getFilteredMoviesFromDb(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getFilteredMoviesFromDb(newText: String) {
        val searchTextQuery = "%$newText%"
        viewModel.getMoviesByName(searchTextQuery).observe(this, Observer {
            movieAdapter = MovieAdapter(this, it, twoPane)
            item_list.adapter = movieAdapter
        })
    }


}
