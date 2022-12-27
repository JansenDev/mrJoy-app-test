package com.my.demo.testlogin.entity

import com.google.gson.annotations.SerializedName

data class MovieDBResponse<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)
