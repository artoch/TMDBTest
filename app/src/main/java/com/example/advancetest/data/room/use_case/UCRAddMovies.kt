package com.example.advancetest.data.room.use_case

import com.example.advancetest.data.room.entities.MovieItemRoom
import com.example.advancetest.data.room.repo.MovieRoomRepo
import javax.inject.Inject

class UCRAddMovies @Inject constructor(
    private val movieRepository: MovieRoomRepo
) {

    suspend operator fun invoke(movie: List<MovieItemRoom>) = movieRepository.addMovies(movie)

}
