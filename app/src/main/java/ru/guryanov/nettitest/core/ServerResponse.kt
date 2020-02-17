package ru.guryanov.nettitest.core

sealed class ServerResponse<out T> {
    data class Success<out T>(val data: T) : ServerResponse<T>()
    data class Error(val exception: Exception) : ServerResponse<Nothing>()
    data class ErrorCode(val errorCode: Int) : ServerResponse<Nothing>()
}