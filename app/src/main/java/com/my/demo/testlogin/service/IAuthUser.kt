package com.my.demo.testlogin.service

import com.my.demo.testlogin.entity.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IAuthUser {

    @GET("login/{user}/{password}")
    fun authUser(@Path("user") user: String, @Path("password") password: String): Call<DataResponse<String>>

}