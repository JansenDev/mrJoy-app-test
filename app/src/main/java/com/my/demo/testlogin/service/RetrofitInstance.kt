package com.my.demo.testlogin.service

import com.my.demo.testlogin.constant.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val AUTH_URL = Constants.authUser.URL_BASE
    private val MOVIEDB_URL = Constants.movieDB.URL_BASE

    private fun getAuthRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getMovieDBRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MOVIEDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieDBService = getMovieDBRetrofit().create(IMovieDBService::class.java)
    val authService = getAuthRetrofit().create(IAuthUser::class.java)

}