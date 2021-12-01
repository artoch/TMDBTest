package com.example.advancetest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.advancetest.common.state.movie_state.MovieState
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.data.domain.MovieTrailersDomain
import com.example.advancetest.data.domain.uc_repo.UCMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UCMovieRepository
): ViewModel(){

    private val _state = MutableStateFlow<MovieState>(MovieState.Nothing)
    val state = _state.asStateFlow()

    private val _movies = MutableStateFlow<PagingData<MovieItemDomain>>(PagingData.empty())
    val movies = _movies.asStateFlow()

    private val _selectedMovie = MutableStateFlow<MovieItemDomain?>(null)
    val selectedMovie = _selectedMovie.asStateFlow()

    private val _selectedMovieTrailer = MutableStateFlow<MovieTrailersDomain?>(null)
    val selectedMovieTrailer = _selectedMovieTrailer.asStateFlow()

    private val _movieTrailer = MutableStateFlow<List<MovieTrailersDomain>>(emptyList())
    val movieTrailer= _movieTrailer.asStateFlow()


    fun selectedMovieTrailer(data: MovieTrailersDomain){
        _selectedMovieTrailer.value = data
    }

    fun setMovie(data: MovieItemDomain){
        _selectedMovie.value = data
    }

    fun clearMovieState(){
        _state.value = MovieState.Nothing
    }

    fun getMovies(){
        viewModelScope.launch {
            repository.ucMoviePaging.invoke().collectLatest { data ->
                _movies.value = data
            }
        }
    }

    fun getMovieTrailer(id:Int){
        viewModelScope.launch {
            repository.ucMovieTrailer.invoke(id).collectLatest { it ->
                _state.value = when{
                    it.isLoading -> MovieState.Loading
                    it.data != null -> setMovieTrailer(it.data)
                    else -> MovieState.Error(it.error!!)
                }
            }
        }
    }

    private fun setMovieTrailer(data: List<MovieTrailersDomain>):MovieState{
        _movieTrailer.value = data
        return MovieState.SuccessMovie(data)
    }
}