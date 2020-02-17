package ru.guryanov.nettitest.data.repository

import ru.guryanov.nettitest.core.ServerResponse
import ru.guryanov.nettitest.data.entity.Post

interface AppDataSource {
    suspend fun getPosts(): ServerResponse<MutableList<Post>>
    suspend fun savePosts(posts: MutableList<Post>)

}