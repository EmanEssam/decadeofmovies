package com.swvl.decadeofmovies.data.service

import com.swvl.decadeofmovies.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitHelper {
    companion object {

        fun makeRetrofitService(): FlickrApiService {

            return Retrofit.Builder()

                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build().create(FlickrApiService::class.java)
        }
    }
}