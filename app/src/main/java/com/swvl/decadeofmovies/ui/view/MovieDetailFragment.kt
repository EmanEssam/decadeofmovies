package com.swvl.decadeofmovies.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.swvl.decadeofmovies.R
import com.swvl.decadeofmovies.data.model.Movie
import com.swvl.decadeofmovies.data.model.Photo
import com.swvl.decadeofmovies.ui.adapter.PhotosAdapter
import com.swvl.decadeofmovies.ui.viewmodel.DetailsActivityViewModel
import com.swvl.decadeofmovies.ui.viewmodel.DetailsActivityViewModelFactory
import kotlinx.android.synthetic.main.movie_detail.view.*
import kotlinx.android.synthetic.main.movie_list_content.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [MovieListActivity]
 * in two-pane mode (on tablets) or a [MovieDetailActivity]
 * on handsets.
 */
class MovieDetailFragment : Fragment(), KodeinAware {

    /**
     * The dummy content this fragment is presenting.
     */
    override val kodein: Kodein by closestKodein()

    private val viewModelFactory: DetailsActivityViewModelFactory by instance()

    private lateinit var selectedMovies: Movie
    private lateinit var photosAdapter: PhotosAdapter
    val photo = MutableLiveData<Photo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey("movie")) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                selectedMovies = it.getSerializable("movie") as Movie


            }
        }
        retainInstance = true

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.movie_detail, container, false)
        selectedMovies.let {
            rootView.movieTitle?.text = selectedMovies.title
            rootView.movie_year?.text = selectedMovies.year
            rootView.movie_cast?.text = selectedMovies.cast.toString().replace("[", "").replace("]", "")
            rootView.movie_genres?.text = selectedMovies.genres.toString().replace("[", "").replace("]", "")
        }

        if (photo.value == null) {
            val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DetailsActivityViewModel::class.java)
            viewModel.getPhotosFromApi(selectedMovies.title).observe(this, Observer { it ->
                photo.value = it
                showPhotoList(rootView)


            })

        } else {
            showPhotoList(rootView)
        }


        return rootView
    }

    private fun showPhotoList(rootView: View) {
        rootView.photoPbar.visibility = View.GONE
        setupPhotoAdapter(rootView, photo)
    }

    private fun setupPhotoAdapter(rootView: View, photo: MutableLiveData<Photo>) {
        rootView.photos_rv.layoutManager = GridLayoutManager(activity, 2)
        photosAdapter = PhotosAdapter(photo)
        rootView.photos_rv.adapter = photosAdapter
        rootView.photos_rv.visibility = View.VISIBLE
        rootView.photos_rv.setHasFixedSize(true)
        photosAdapter.notifyDataSetChanged()

    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
