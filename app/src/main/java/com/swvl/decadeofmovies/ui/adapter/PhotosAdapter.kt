package com.swvl.decadeofmovies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swvl.decadeofmovies.R
import com.swvl.decadeofmovies.data.model.Photo
import com.swvl.decadeofmovies.utils.PhotoBuilder.Companion.getPhotoUrl
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter(
    private val values: MutableLiveData<Photo>
) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val item = values.value!!.photos.photo[position]
            Glide.with(context).load(getPhotoUrl(item.farm, item.server, item.id, item.secret)).into(holder.movieImage)

        }
    }

    override fun getItemCount() = values.value!!.photos.photo.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val movieImage: ImageView = view.movie_photo
    }
}