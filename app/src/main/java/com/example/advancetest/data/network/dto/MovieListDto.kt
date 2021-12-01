package com.example.advancetest.data.network.dto

import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.data.domain.ResultDomain
import com.google.gson.annotations.SerializedName


data class ResultDto(
    val page:Int,
    @SerializedName("total_pages") val totalPage:Int,
    val results: List<MovieItemDto>
)

fun ResultDto.toDomain() = ResultDomain(
    page, totalPage, results.map { it.mapToDomain() }
)

data class MovieItemDto (
    val id: Int,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("poster_path") val posterPath: String,
)

fun MovieItemDto.mapToDomain() = MovieItemDomain(
    id, title, voteAverage, voteCount, posterPath
)



