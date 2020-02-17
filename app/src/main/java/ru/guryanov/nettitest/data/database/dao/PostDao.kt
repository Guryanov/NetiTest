package ru.guryanov.nettitest.data.database.dao

import androidx.room.*
import ru.guryanov.nettitest.data.entity.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    suspend fun getPosts(): MutableList<Post>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post: MutableList<Post>)
}