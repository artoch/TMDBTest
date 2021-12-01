package com.example.advancetest.data.network.use_case

import com.example.advancetest.common.base.BaseUseCase
import com.example.advancetest.data.network.repository.MovieRepository
import javax.inject.Inject

class UCMoviePaging @Inject constructor(
    private val repository: MovieRepository
): BaseUseCase() {

    suspend operator fun invoke() = repository.getMoviePaging()

}