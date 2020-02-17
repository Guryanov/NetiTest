package ru.guryanov.nettitest.core

import retrofit2.Response
import retrofit2.http.GET
import ru.guryanov.nettitest.data.entity.Post

interface NettiApi {

    @GET("/posts")
    suspend fun getData(): Response<MutableList<Post>>

}