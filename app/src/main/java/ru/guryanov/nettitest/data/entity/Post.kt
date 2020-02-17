package ru.guryanov.nettitest.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Post(
    @SerializedName("userId")
    @Expose
    @ColumnInfo
    val userId: Int,
    @SerializedName("id")
    @Expose
    @PrimaryKey
    val id: Int,
    @SerializedName("title")
    @Expose
    @ColumnInfo
    val title: String,
    @SerializedName("body")
    @Expose
    @ColumnInfo
    val body: String)