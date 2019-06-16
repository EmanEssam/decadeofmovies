package com.swvl.decadeofmovies.ui.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swvl.decadeofmovies.R
import com.swvl.decadeofmovies.data.model.Movie
import com.swvl.decadeofmovies.ui.view.MovieDetailActivity
import com.swvl.decadeofmovies.ui.view.MovieDetailFragment
import com.swvl.decadeofmovies.ui.view.MovieListActivity
import kotlinx.android.synthetic.main.movie_list_content.view.*

class MovieAdapter(
    private val parentActivity: MovieListActivity,
    private val values: List<Movie>,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    private lateinit var selectedMovie: Movie


    init {
        onClickListener = View.OnClickListener { v ->

            //            val item = v.tag as DummyContent.DummyItem
            if (twoPane) {
                val fragment = MovieDetailFragment().apply {
                    arguments = Bundle().apply {
                        //                        putString(ItemDetailFragment.ARG_ITEM_ID, item.id)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, MovieDetailActivity::class.java).apply {
                    //                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
                    putExtra("movie", selectedMovie)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.year
        holder.genres.text = item.genres.toString()
        holder.ratingBar.rating = item.rating.toFloat()

        with(holder.itemView) {
            tag = item
            selectedMovie = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.movieTitle
        val contentView: TextView = view.year
        val genres: TextView = view.genres
        val ratingBar: RatingBar = view.ratingBar
    }
}