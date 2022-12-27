package com.my.demo.testlogin.service

import com.my.demo.testlogin.entity.Movie
import com.my.demo.testlogin.entity.MovieDBResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IMovieDBService {

    @Headers("Accept: application/json")
    @GET("movie/popular")
    fun getAllMovies(@Query("api_key") api_key: String):Call<MovieDBResponse<Movie>>

}