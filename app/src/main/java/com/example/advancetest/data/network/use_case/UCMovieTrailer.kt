package com.example.advancetest.data.network.use_case

import com.example.advancetest.common.base.BaseUseCase
import com.example.advancetest.common.state.DataState
import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.data.domain.ResultTrailerDomain
import com.example.advancetest.data.network.repository.MovieRepository
import javax.inject.Inject

class UCMovieTrailer  @Inject constructor(
    private val repository: MovieRepository
): BaseUseCase() {

    operator fun invoke(id: Int = 763164) = myFlow<List<MovieTrailersDomain>> {
        emit(DataState.loading())
        val data = repository.getMovieTrailer(id)
        if (data.data != null){
            emit(DataState.data(data.data))
        }else{
            emit(DataState.errorData(data.error!!))
        }
    }
}