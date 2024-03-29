package com.swvl.decadeofmovies.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.swvl.decadeofmovies.data.model.Movie
import com.swvl.decadeofmovies.data.model.MovieData
import com.swvl.decadeofmovies.utils.Converters
import java.util.concurrent.Executors

const val DATABASE_NAME = "movies_database"
@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao


    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {

            if (instance == null) {

                synchronized(MovieDatabase::class) {

                    instance = Room.databaseBuilder(

                        context.applicationContext,

                        MovieDatabase::class.java, DATABASE_NAME

                    ).addCallback(object : RoomDatabase.Callback() {

                        override fun onCreate(db: SupportSQLiteDatabase) {

                            super.onCreate(db)

                            Executors.newSingleThreadScheduledExecutor().execute {

                                val movieJsonData = MovieData.readMovies(context)

                                getInstance(context)!!.movieDao().insertAll(MovieData.parseMovies(movieJsonData))
                            }
                        }
                    })
                        .build()
                }
            }
            return instance
        }


    }

}
