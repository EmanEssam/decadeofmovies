package com.swvl.decadeofmovies.utils

const val PHOTO_BASE_URL = "https://farm"
const val SLASH = "/"
const val UNDERSCORE = "_"
const val JPG = ".jpg"

class PhotoBuilder {

    companion object {

        fun getPhotoUrl(farm: Int, server: String, id: String, secret: String): String {

            return "$PHOTO_BASE_URL$farm.staticflickr.com/$server$SLASH$id$UNDERSCORE$secret$JPG".trim()
        }

    }

}