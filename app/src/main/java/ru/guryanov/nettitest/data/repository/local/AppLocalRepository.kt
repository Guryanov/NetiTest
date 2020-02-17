package ru.guryanov.nettitest.data.repository.local

import ru.guryanov.nettitest.core.ServerResponse
import ru.guryanov.nettitest.core.ioTask
import ru.guryanov.nettitest.data.database.dao.PostDao
import ru.guryanov.nettitest.data.entity.Post
import ru.guryanov.nettitest.data.repository.AppDataSource

class AppLocalRepository constructor(private val postDao: PostDao) : AppDataSource {

    companion object {
        const val NO_DATA = -100
    }


    override suspend fun getPosts(): ServerResponse<MutableList<Post>> {
        return ioTask {
            val data = postDao.getPosts()
            return@ioTask if (data.isNullOrEmpty()) {
                ServerResponse.ErrorCode(NO_DATA)
            } else {
                ServerResponse.Success(data)
            }
        }
    }

    override suspend fun savePosts(posts: MutableList<Post>) {
        postDao.insertPosts(posts)
    }
}