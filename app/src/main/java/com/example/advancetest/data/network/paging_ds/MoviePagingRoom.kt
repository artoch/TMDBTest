package com.example.advancetest.data.network.paging_ds

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.advancetest.common.const.INITIAL_PAGE_INDEX
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.room.entities.toDomain
import com.example.advancetest.data.room.use_case.UCRGetMovies

class MoviePagingRoom constructor(
    private val movies: UCRGetMovies
) : PagingSource<Int, MovieItemDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemDomain> {
        val pageNumber = params.key ?: INITIAL_PAGE_INDEX
        val randomMovies = movies.invoke(params.loadSize)
        val result = randomMovies.map { it.toDomain() }
        return LoadResult.Page(
            data = result,
            prevKey = if (pageNumber == INITIAL_PAGE_INDEX) null else pageNumber - 1,
            nextKey = if (result.isNullOrEmpty()) null else pageNumber + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemDomain>): Int? = null
}