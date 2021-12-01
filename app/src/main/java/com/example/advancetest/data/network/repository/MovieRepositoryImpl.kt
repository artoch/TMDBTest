package com.example.advancetest.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.advancetest.common.error.ErrorEntity
import com.example.advancetest.common.extension.toErrorEntity
import com.example.advancetest.common.state.DataState
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.data.network.dto.toDomain
import com.example.advancetest.data.network.paging_ds.MoviePagingDT
import com.example.advancetest.data.network.service.MovieService
import com.example.advancetest.data.room.use_case.UCRAddMovies
import com.example.advancetest.data.room.use_case.UCRGetMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val ucrAddMovies: UCRAddMovies,
    private val ucrGetMovies: UCRGetMovies
):MovieRepository {

    override suspend fun getMoviePaging(): Flow<PagingData<MovieItemDomain>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { MoviePagingDT(service, ucrAddMovies, ucrGetMovies) }
    ).flow

    override suspend fun getMovieTrailer(page: Int): DataState<List<MovieTrailersDomain>>{
        val data = service.getTrailerMovies(page)
        return if (data.isSuccessful){
            DataState.data(data.body()?.toDomain()?.results)
        }else{
            DataState.errorData(if (data.errorBody() != null) data.errorBody()!!.toErrorEntity() else ErrorEntity() )
        }
    }
}