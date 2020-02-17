package ru.guryanov.nettitest.base

import retrofit2.Response
import ru.guryanov.nettitest.BuildConfig
import ru.guryanov.nettitest.core.NettiApi
import ru.guryanov.nettitest.core.ServerResponse
import ru.guryanov.nettitest.data.entity.Post

open class BaseRepository {


    protected val dataUrl: String = BuildConfig.DATA_URL


    open suspend fun <T,K : Any> getRemoteData(entity:Class<K>, api: NettiApi): ServerResponse<T> {
        return try {
            with(entity) {
                when {
                    isAssignableFrom(Post::class.java) -> {
                        apiCall(api.getData())
                    }
                    else ->
                        throw IllegalArgumentException("Unknown entity: ${entity.name}")
                }
            } as ServerResponse<T>
        } catch (e: Exception) {
            ServerResponse.Error(e)
        }
    }

    private fun <T : Any> apiCall(api: Response<T>): ServerResponse<T> {
        return if (api.isSuccessful) {
            ServerResponse.Success(api.body()!!)
        } else {
            ServerResponse.ErrorCode(api.code())
        }
    }
}