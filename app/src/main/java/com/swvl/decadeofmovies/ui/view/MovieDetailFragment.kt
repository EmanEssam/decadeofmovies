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
import com.swvl.decadeofmovies.data.service.RetrofitHelper
import com.swvl.decadeofmovies.ui.adapter.PhotosAdapter
import com.swvl.decadeofmovies.ui.viewmodel.DetailsActivityViewModel
import com.swvl.decadeofmovies.ui.viewmodel.DetailsActivityViewModelFactory
import com.swvl.decadeofmovies.utils.MOVIE_KEY
import kotlinx.android.synthetic.main.movie_detail.view.*
import kotlinx.android.synthetic.main.movie_list_content.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class MovieDetailFragment : Fragment(), KodeinAware {


    override val kodein: Kodein by closestKodein() // Kodein DI instance

    private val viewModelFactory: DetailsActivityViewModelFactory by instance() // get viewModelFactory instance by Kodein

    private lateinit var selectedMovies: Movie
    private lateinit var photosAdapter: PhotosAdapter
    private val photo = MutableLiveData<Photo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(MOVIE_KEY)) {

                selectedMovies = it.getSerializable(MOVIE_KEY) as Movie


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

        val service = RetrofitHelper.makeRetrofitService()
        if (photo.value == null) {
            val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DetailsActivityViewModel::class.java)
            viewModel.getPhotosFromApi(selectedMovies.title, service).observe(this, Observer {
                photo.value = it
                if (photo.value == null) {

                    showErrorScreen(rootView)
                } else {

                    showPhotoList(rootView)
                }


            })

        } else {
            showPhotoList(rootView)
        }


        return rootView
    }

    private fun showErrorScreen(rootView: View) {

        rootView.photoPbar.visibility = View.GONE

        rootView.error_tv.visibility = View.VISIBLE

        rootView.photos_rv.visibility = View.GONE


    }

    private fun showPhotoList(rootView: View) {
        rootView.photoPbar.visibility = View.GONE
        rootView.error_tv.visibility = View.GONE
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

        const val ARG_ITEM_ID = "item_id"
    }
}
