package ru.guryanov.nettitest

import android.app.Application
import ru.guryanov.nettitest.data.ServiceLocator
import ru.guryanov.nettitest.data.repository.remote.AppRepository

class NettiApplication : Application() {
    val taskRepository: AppRepository
        get() = ServiceLocator.provideAppRepository(this)
}