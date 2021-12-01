package com.example.advancetest.data.network.api

import com.example.advancetest.data.network.dto.ResultDto
import com.example.advancetest.data.network.dto.ResultTrailerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("page") page:Int
    ): Response<ResultDto>

    @GET("{id}/videos")
    suspend fun getTrailerMovies(
        @Path("id") id:Int
    ): Response<ResultTrailerDto>
}