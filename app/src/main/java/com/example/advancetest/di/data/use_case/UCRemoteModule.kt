package com.example.advancetest.di.data.use_case

import com.example.advancetest.data.network.repository.MovieRepository
import com.example.advancetest.data.network.use_case.UCMoviePaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object UCRemoteModule {

    @Singleton
    @Provides
    fun provideUCMoviePaging(
        repository: MovieRepository
    ): UCMoviePaging = UCMoviePaging(repository)
}