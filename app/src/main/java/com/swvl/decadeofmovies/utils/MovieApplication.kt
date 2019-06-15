package com.swvl.decadeofmovies.utils

import android.app.Application
import com.swvl.decadeofmovies.data.database.DatabaseImpl
import com.swvl.decadeofmovies.data.database.MovieDao
import com.swvl.decadeofmovies.data.repository.MovieRepository
import com.swvl.decadeofmovies.data.repository.MovieRepositoryImpl
import com.swvl.decadeofmovies.data.repository.PhotosRepository
import com.swvl.decadeofmovies.data.repository.PhotosRepositoryImpl
import com.swvl.decadeofmovies.data.service.RetrofitHelper
import com.swvl.decadeofmovies.ui.viewmodel.DetailsActivityViewModel
import com.swvl.decadeofmovies.ui.viewmodel.DetailsActivityViewModelFactory
import com.swvl.decadeofmovies.ui.viewmodel.MovieViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MovieApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind<com.swvl.decadeofmovies.data.database.Database>() with singleton { DatabaseImpl() }
        bind<MovieDao>() with singleton { instance<com.swvl.decadeofmovies.data.database.Database>().movieDao }
        bind<MovieRepository>() with singleton { MovieRepositoryImpl(instance(), applicationContext) }
        bind() from provider { MovieViewModelFactory(instance()) }
        bind() from provider { RetrofitHelper() }
        bind<PhotosRepository>() with singleton { PhotosRepositoryImpl() }
        bind() from provider { DetailsActivityViewModel(instance()) }
        bind() from provider { DetailsActivityViewModelFactory(instance()) }


    }
}