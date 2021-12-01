package com.example.advancetest.data.network.repository

import androidx.paging.PagingData
import com.example.advancetest.common.state.DataState
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.domain.MovieTrailersDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMoviePaging(): Flow<PagingData<MovieItemDomain>>

    suspend fun getMovieTrailer(page:Int): DataState<List<MovieTrailersDomain>>
}