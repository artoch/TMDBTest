package com.example.advancetest.common.state.movie_state

import com.example.advancetest.common.error.ErrorDomain
import com.example.advancetest.data.domain.MovieTrailersDomain

sealed class MovieState {
    object Loading: MovieState()
    object Success: MovieState()
    object Nothing: MovieState()
    data class SuccessMovie(val data: List<MovieTrailersDomain>, val title: String):MovieState()
    data class Error(val error:ErrorDomain): MovieState()
}