package com.example.advancetest.data.room.repo

import com.example.advancetest.data.room.entities.MovieItemRoom
import kotlinx.coroutines.flow.Flow

interface MovieRoomRepo {

    suspend fun getMovies(size:Int): List<MovieItemRoom>

    suspend fun addMovies(movie: List<MovieItemRoom>)
}