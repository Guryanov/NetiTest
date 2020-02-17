package ru.guryanov.nettitest.domain

import ru.guryanov.nettitest.core.ServerResponse
import ru.guryanov.nettitest.data.entity.Post
import ru.guryanov.nettitest.data.repository.remote.AppRepository

class GetPostsUseCase(private val appRepository: AppRepository) {
    suspend operator fun invoke(): ServerResponse<MutableList<Post>> {
        val response = appRepository.getPosts()
        if (response is ServerResponse.Success) {
            appRepository.savePosts(response.data)
        }
        return response
    }
}