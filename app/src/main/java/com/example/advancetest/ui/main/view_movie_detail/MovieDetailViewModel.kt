package com.example.advancetest.ui.main.view_movie_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(): ViewModel(){

    private val _time = MutableStateFlow(0F)
    val time = _time.asStateFlow()


    fun setTime(data: Float){
        _time.value = data
    }

}