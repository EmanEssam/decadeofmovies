package com.swvl.decadeofmovies.data.database

class DatabaseImpl :Database  {

    override val movieDao: MovieDao = MovieDaoImpl()
}
