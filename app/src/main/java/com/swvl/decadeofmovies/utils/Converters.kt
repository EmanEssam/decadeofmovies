package com.swvl.decadeofmovies.utils

import androidx.room.TypeConverter
import com.google.gson.Gson


class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : com.google.gson.reflect.TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}