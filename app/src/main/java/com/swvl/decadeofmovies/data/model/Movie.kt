package com.swvl.decadeofmovies.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    val title: String,
    val year: String,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
): Serializable
