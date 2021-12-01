package com.example.advancetest.data.network.dto

import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.data.domain.ResultTrailerDomain

data class ResultTrailerDto(
    val id:Int,
    val results: List<MovieTrailersDto>
)

fun ResultTrailerDto.toDomain() = ResultTrailerDomain(
    id, results.map { it.mapToDomain() }
)

data class MovieTrailersDto(
    val id: String,
    val key: String,
    val site: String,
    val name: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val published_at: String,
    )

fun MovieTrailersDto.mapToDomain() = MovieTrailersDomain(
    id, key, site, name, size, type, official, published_at
)
