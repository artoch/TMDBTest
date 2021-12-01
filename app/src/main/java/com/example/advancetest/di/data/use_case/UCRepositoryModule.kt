package com.example.advancetest.di.data.use_case

import com.example.advancetest.data.domain.uc_repo.UCMovieRepository
import com.example.advancetest.data.network.use_case.UCMoviePaging
import com.example.advancetest.data.network.use_case.UCMovieTrailer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object UCRepositoryModule {

    @Singleton
    @Provides
    fun provideUCMovieRepository(
        ucMoviePaging: UCMoviePaging,
        ucMovieTrailer: UCMovieTrailer
    ): UCMovieRepository = UCMovieRepository(
        ucMoviePaging,
        ucMovieTrailer
    )
}