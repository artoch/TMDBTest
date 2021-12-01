package com.example.advancetest.data.network.service

import com.example.advancetest.data.network.dto.ResultDto
import com.example.advancetest.data.network.dto.ResultTrailerDto
import retrofit2.Response

interface MovieService {

    suspend fun getPopularMovies(page:Int): Response<ResultDto>

    suspend fun getTrailerMovies(id:Int): Response<ResultTrailerDto>
}