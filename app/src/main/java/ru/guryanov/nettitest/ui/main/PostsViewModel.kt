package ru.guryanov.nettitest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.guryanov.nettitest.core.ServerResponse
import ru.guryanov.nettitest.core.uiJob
import ru.guryanov.nettitest.data.entity.Post
import ru.guryanov.nettitest.domain.GetPostsUseCase

class PostsViewModel(val useCase: GetPostsUseCase) : ViewModel() {

    private val _items = MutableLiveData<MutableList<Post>>().apply { value = mutableListOf() }
    val items: LiveData<MutableList<Post>> = _items

    private val _loadError=MutableLiveData<Boolean>()
    val loadError: LiveData<Boolean> = _loadError

    open fun getData() {
        viewModelScope.uiJob {
            useCase.invoke().let { response ->
                if (response is ServerResponse.Success) {
                    _items.value = response.data
                } else {
                    _loadError.value=true
                }
            }
        }
    }


}
