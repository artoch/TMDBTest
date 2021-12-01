package com.example.advancetest.data.network.service

import com.example.advancetest.data.network.api.ApiService

class MovieServiceImpl(private val service: ApiService): MovieService {

    override suspend fun getPopularMovies(page:Int) = service.getPopularMovies(page)

    override suspend fun getTrailerMovies(id:Int) = service.getTrailerMovies(id)


}