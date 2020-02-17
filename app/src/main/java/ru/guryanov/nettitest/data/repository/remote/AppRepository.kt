package ru.guryanov.nettitest.data.repository.remote

import ru.guryanov.nettitest.base.BaseRepository
import ru.guryanov.nettitest.core.NetworkProvider
import ru.guryanov.nettitest.core.ServerResponse
import ru.guryanov.nettitest.data.entity.Post
import ru.guryanov.nettitest.data.repository.AppDataSource

class AppRepository(private val localData: AppDataSource) : BaseRepository(), AppDataSource {


    override suspend fun getPosts(): ServerResponse<MutableList<Post>> {
        val localData = localData.getPosts()
        if (localData is ServerResponse.Success) {
            return localData
        }
        return getRemoteData(Post::class.java, NetworkProvider.createNetworkProvider(dataUrl))
    }

    override suspend fun savePosts(posts: MutableList<Post>) {
        localData.savePosts(posts)
    }
}