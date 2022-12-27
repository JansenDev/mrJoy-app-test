package com.my.demo.testlogin.entity

data class DataResponse<T>(
    val data: T,
    val message: String,
    val status: Int
)