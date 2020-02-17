package ru.guryanov.nettitest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.guryanov.nettitest.data.database.dao.PostDao
import ru.guryanov.nettitest.data.entity.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDataBase :RoomDatabase(){

    abstract fun taskDao(): PostDao

}