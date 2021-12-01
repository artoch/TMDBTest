package com.example.advancetest.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.advancetest.data.domain.MovieItemDomain

@Entity(tableName = "movie_table")
data class MovieItemRoom (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int,
    val posterPath: String,
)

fun MovieItemRoom.toDomain() = MovieItemDomain(
    id, title, voteAverage, voteCount, posterPath
)