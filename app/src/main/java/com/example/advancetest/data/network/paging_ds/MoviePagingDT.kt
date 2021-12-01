package com.example.advancetest.data.network.paging_ds

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.advancetest.common.const.INITIAL_PAGE_INDEX
import com.example.advancetest.common.const.INITIAL_PAGE_INDEX_REMOTE
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.domain.toRoom
import com.example.advancetest.data.network.dto.mapToDomain
import com.example.advancetest.data.network.service.MovieService
import com.example.advancetest.data.room.entities.toDomain
import com.example.advancetest.data.room.use_case.UCRAddMovies
import com.example.advancetest.data.room.use_case.UCRGetMovies
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class MoviePagingDT constructor(
    private val service: MovieService,
    private val ucrAddMovies: UCRAddMovies,
    private val ucrGetMovies: UCRGetMovies
) : PagingSource<Int, MovieItemDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemDomain> {
        val pageNumber = params.key ?: INITIAL_PAGE_INDEX_REMOTE
        return try {
            val response = service.getPopularMovies(pageNumber)
            delay(2000L)
            val pagedResponse = response.body()
            val data = pagedResponse?.results?.map { it.mapToDomain() }.also { itr ->
                if (itr != null) {
                    ucrAddMovies.invoke(itr.map { it.toRoom() })
                }
            }
            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = pageNumber + 1
            )
        } catch (e: IOException) {
            getLocalData(params , e)
        } catch (e: HttpException) {
            getLocalData(params, e)
        } catch (e: Exception) {
            getLocalData(params, e)
        }
    }

    private suspend fun getLocalData(params: LoadParams<Int>, e: Exception): LoadResult<Int, MovieItemDomain>{
        val pageNumberLocal = params.key ?: INITIAL_PAGE_INDEX
        val randomMovies = ucrGetMovies.invoke(params.loadSize)
        val result = randomMovies.map { it.toDomain() }
        return if (result.isNotEmpty()) {
            LoadResult.Page(
                data = result,
                prevKey = if (pageNumberLocal == INITIAL_PAGE_INDEX) null else pageNumberLocal - 1,
                nextKey = if (result.isNullOrEmpty()) null else pageNumberLocal + 1
            )
        }else{
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemDomain>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }
}