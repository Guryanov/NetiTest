package ru.guryanov.nettitest.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.guryanov.nettitest.data.repository.remote.AppRepository
import ru.guryanov.nettitest.domain.GetPostsUseCase
import ru.guryanov.nettitest.ui.main.PostsViewModel

class ViewModelFactory constructor(private val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(PostsViewModel::class.java) ->
                    PostsViewModel(GetPostsUseCase(appRepository))
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}