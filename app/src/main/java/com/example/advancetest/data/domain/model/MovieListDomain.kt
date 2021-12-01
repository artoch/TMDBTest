package com.example.advancetest.data.domain

import com.example.advancetest.data.room.entities.MovieItemRoom

data class ResultDomain(
    val page:Int,
    val totalPage:Int,
    val results: List<MovieItemDomain>
)

data class MovieItemDomain (
    val id: Int,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int,
    val posterPath: String,
)

fun MovieItemDomain.toRoom() = MovieItemRoom(
    id, title, voteAverage, voteCount, posterPath
)

