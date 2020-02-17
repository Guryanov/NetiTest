package ru.guryanov.nettitest.data

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import ru.guryanov.nettitest.data.database.AppDataBase
import ru.guryanov.nettitest.data.repository.AppDataSource
import ru.guryanov.nettitest.data.repository.local.AppLocalRepository
import ru.guryanov.nettitest.data.repository.remote.AppRepository

object ServiceLocator {

    private val lock = Any()
    private var database: AppDataBase? = null
    @Volatile
    var tasksRepository: AppRepository? = null
        @VisibleForTesting set

    fun provideAppRepository(context: Context): AppRepository {
        synchronized(this) {
            return tasksRepository ?: tasksRepository ?: createAppRepository(context)
        }
    }

    private fun createAppRepository(context: Context): AppRepository {
        return AppRepository(createAppLocalDataSource(context))
    }

    private fun createAppLocalDataSource(context: Context): AppDataSource {
        val database = database ?: createDataBase(context)
        return AppLocalRepository(database.taskDao())
    }

    private fun createDataBase(context: Context): AppDataBase {
        val result = Room.databaseBuilder(
            context.applicationContext, AppDataBase::class.java, "Netti.db"
        ).build()
        database = result
        return result
    }
}