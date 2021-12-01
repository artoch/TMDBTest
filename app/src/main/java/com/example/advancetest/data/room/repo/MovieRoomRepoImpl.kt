package com.example.advancetest.data.room.repo

import com.example.advancetest.data.room.dao.MovieDao
import com.example.advancetest.data.room.entities.MovieItemRoom
import javax.inject.Inject

class MovieRoomRepoImpl @Inject
    constructor(private val movieDao: MovieDao)
    :MovieRoomRepo {

    override suspend fun getMovies(size:Int) = movieDao.getMovies(size)

    override suspend fun addMovies(movie: List<MovieItemRoom>) {
        movieDao.addMovies(movie)
    }
}