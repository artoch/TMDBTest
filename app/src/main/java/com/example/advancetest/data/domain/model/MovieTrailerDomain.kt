package com.example.advancetest.data.domain

data class ResultTrailerDomain(
    val id:Int,
    val results: List<MovieTrailersDomain>
)

data class MovieTrailersDomain(
    val id: String,
    val key: String,
    val site: String,
    val name: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val published_at: String,
)