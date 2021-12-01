package com.example.advancetest.data.room.use_case

import com.example.advancetest.data.room.repo.MovieRoomRepo
import javax.inject.Inject

class UCRGetMovies @Inject constructor(
    private val movieRepository: MovieRoomRepo
) {

    suspend operator fun invoke(size:Int) = movieRepository.getMovies(size)

}