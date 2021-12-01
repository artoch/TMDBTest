package com.example.advancetest.data.domain.uc_repo

import com.example.advancetest.data.network.use_case.UCMoviePaging
import com.example.advancetest.data.network.use_case.UCMovieTrailer
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
data class UCMovieRepository @Inject constructor(
    val ucMoviePaging: UCMoviePaging,
    val ucMovieTrailer: UCMovieTrailer,
)